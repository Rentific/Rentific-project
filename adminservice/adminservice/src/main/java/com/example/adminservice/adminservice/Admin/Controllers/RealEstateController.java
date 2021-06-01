package com.example.adminservice.adminservice.Admin.Controllers;

import com.example.adminservice.adminservice.Admin.Dtos.ReservedRealEstate;
import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Models.ImageModel;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.RabbitMQ.AdminServiceSender;
import com.example.adminservice.adminservice.Admin.Repositories.ImageRepository;
import com.example.adminservice.adminservice.Admin.Services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.example.adminservice.adminservice.Admin.Models.Response;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping(path="/real-estate")
public class RealEstateController {
    private final RealEstateService _realEstateService;

    @Autowired
    RestTemplate restTemplate = new RestTemplate();

    private final AdminServiceSender sender;

    public RealEstateController(RealEstateService realEstateService, AdminServiceSender sender) {
        _realEstateService = realEstateService;
        this.sender = sender;
    }

    Map<String, Object> createResponse(List<RealEstate> realEstates, Page<RealEstate> pageRealEstates) {
        Map<String, Object> response = new HashMap<>();
        response.put("realEstates", realEstates);
        response.put("currentPage", pageRealEstates.getNumber());
        response.put("totalItems", pageRealEstates.getTotalElements());
        response.put("totalPages", pageRealEstates.getTotalPages());

        return response;
    }

    private Sort.Direction getSortDirection(String direction) {
        if (direction.toLowerCase(Locale.ROOT).equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.toLowerCase(Locale.ROOT).equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    private List<Order> getOrders(String[] sort) {
        List<Order> orders = new ArrayList<Order>();

        if (sort[0].contains(",")) {
            // will sort more than 2 columns
            for (String sortOrder : sort) {
                // sortOrder="column, direction"
                String[] _sort = sortOrder.split(",");
                orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[column, direction]
            orders.add(new Order(getSortDirection(sort[1]), sort[0]));
        }
        return orders;
    }

    /* Get all real estates for specific page or sorted by some column */
    @GetMapping(path="/all/real-estates")
    ResponseEntity<Map<String, Object>> findAllRealEstates(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price,asc") String[] sort) {
        try {
            List<Order> orders = getOrders(sort);

            List<RealEstate> realEstates = new ArrayList<RealEstate>();
            Pageable paging = PageRequest.of(page, size, Sort.by(orders));

            Page<RealEstate> pageRealEstates = _realEstateService.findAllRealEstates(paging).getBody();

            if (pageRealEstates != null)
                realEstates = pageRealEstates.getContent();
            else throw new RealEstateNotFoundException("No results found.");

            Map<String, Object> response = createResponse(realEstates, pageRealEstates);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Search real estates with keyword */
    @RequestMapping("/")
    ResponseEntity<Response> searchAllRealEstates(@Param("keyword") String keyword,
                                                                @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
                                                                @RequestParam(defaultValue = "price,asc") String[] sort)
            throws InvalidRequestException, RealEstateNotFoundException, IOException
    {
        try {
            List<Order> orders = getOrders(sort);

            List<RealEstate> realEstates = new ArrayList<RealEstate>();
            List<RealEstate> freeRealEstates = new ArrayList<RealEstate>();


                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                HttpEntity<ReservedRealEstate[]> entity = new HttpEntity<ReservedRealEstate[]>(headers);

                var response = restTemplate.exchange("http://rentservice/realEstate/allReserved", HttpMethod.GET,
                        entity, ReservedRealEstate[].class).getBody();

                
                List<ReservedRealEstate> reservedRealEstates = Arrays.asList(response);

            reservedRealEstates.forEach(realEstate1 -> {
                    try {
                        ResponseEntity<RealEstate> realEstateToUpdate = _realEstateService.findRealEstateById(realEstate1.getRealEstateId());
                        realEstateToUpdate.getBody().setIsReservated(true);
                        _realEstateService.updateExistingRealEstate(realEstateToUpdate.getBody().getRealEstateId(), realEstateToUpdate.getBody());
                    } catch (InvalidRequestException e) {
                        e.printStackTrace();
                    } catch (RealEstateNotFoundException e) {
                        e.printStackTrace();
                    }
                });

            Pageable paging = PageRequest.of(page, size, Sort.by(orders));

            Page<RealEstate> pageRealEstates;

            if (keyword == null)
                pageRealEstates = _realEstateService.findAllRealEstates(paging).getBody();
            else
                pageRealEstates = _realEstateService.listAll(keyword, paging).getBody();


            //else throw new RealEstateNotFoundException("No results found.");

            //Map<String, Object> response2 = createResponse(freeRealEstates, pageRealEstates);
            //return new ResponseEntity<>(response2, HttpStatus.OK);

            List<RealEstate> finalRealEstates = new ArrayList<RealEstate>();


            pageRealEstates.getContent().forEach(realEstate -> {
                List<ImageModel> compressedImages = new ArrayList<ImageModel>();
                realEstate.getImageModel().forEach(imageModel -> {
                    try {
                        ImageModel compressedImage = getImage(imageModel.getId());
                        compressedImages.add(compressedImage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                finalRealEstates.add(new RealEstate(realEstate, compressedImages));
            });

            return new ResponseEntity<Response>(new Response(finalRealEstates, pageRealEstates.getNumber(),
                    pageRealEstates.getSize(), pageRealEstates.getTotalPages(), pageRealEstates.getNumberOfElements()), HttpStatus.OK);

       } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<RealEstate> findRealEstateById(@PathVariable(value = "id") Integer id) throws InvalidRequestException, RealEstateNotFoundException {
        return this._realEstateService.findRealEstateById(id);
    }

    @PostMapping(path="/add")
    RealEstate addNewRealEstate (@RequestBody RealEstate realEstate) throws InvalidRequestException, RealEstateNotFoundException, IOException {
        return sender.send(realEstate);
       /*_realEstateService.saveRealEstate(realEstate);*/
    }

    // @PostMapping(path="/add")
    // void addNewRealEstate (@RequestBody RealEstate realEstate) throws InvalidRequestException, RealEstateNotFoundException, IOException {

    //     sender.send(realEstate);
    //     //return _realEstateService.saveRealEstate(realEstate);
    // }

    @DeleteMapping("delete/{id}")
    void deleteUser(@PathVariable(value = "id") Integer id) throws InvalidRequestException, RealEstateNotFoundException {
        sender.send(id);
        //return this._realEstateService.deleteRealEstate(id);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<RealEstate> updateUser(@PathVariable(value = "id") Integer id, @RequestBody RealEstate realEstate)
            throws InvalidRequestException, RealEstateNotFoundException {
        return this._realEstateService.updateExistingRealEstate(id, realEstate);
    }

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/image/upload/{realEstateId}")
    public ResponseEntity<ImageModel> uploadImage(@PathVariable(value = "realEstateId") Integer id, @RequestParam("imageFile") MultipartFile file) throws InvalidRequestException, RealEstateNotFoundException, IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ResponseEntity<RealEstate> realEstate = _realEstateService.findRealEstateById(id);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressZLib(file.getBytes()), realEstate.getBody());
        ImageModel imageModel = imageRepository.save(img);
        return new ResponseEntity(img, HttpStatus.OK);
    }

    @PutMapping("/image/update/{realEstateId}")
    ResponseEntity<ImageModel> updateUser(@PathVariable(value = "realEstateId") Integer realEstateId, @RequestParam("imageFile") MultipartFile file) throws InvalidRequestException, RealEstateNotFoundException, IOException {
        ResponseEntity<RealEstate> realEstate = _realEstateService.findRealEstateById(realEstateId);
        Optional<ImageModel> imageToUpdate = imageRepository.findByRealEstate(realEstate.getBody());
        ImageModel img = new ImageModel(imageToUpdate.get().getId(), file.getOriginalFilename(), file.getContentType(),
                compressZLib(file.getBytes()), imageToUpdate.get().getRealEstate());
        imageRepository.save(img);
        return new ResponseEntity(imageToUpdate, HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{imageId}" })
    public ImageModel getImage(@PathVariable("imageId") Long imageId) throws IOException {

        final Optional<ImageModel> retrievedImage = imageRepository.findById(imageId);
        ImageModel img = new ImageModel(retrievedImage.get().getId(), retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressZLib(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressZLib(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressZLib(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}


