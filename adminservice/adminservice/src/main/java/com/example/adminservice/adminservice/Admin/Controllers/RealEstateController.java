package com.example.adminservice.adminservice.Admin.Controllers;

import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;



import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path="/real-estate")
public class RealEstateController {
    @Autowired
    private final RealEstateService _realEstateService;

    public RealEstateController(RealEstateService realEstateService) {
        _realEstateService = realEstateService;
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
    ResponseEntity<Map<String, Object>> searchAllRealEstates(RealEstate realEstate, @Param("keyword") String keyword,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price,asc") String[] sort)
    {
        try {
            List<Order> orders = getOrders(sort);

            List<RealEstate> realEstates = new ArrayList<RealEstate>();
            Pageable paging = PageRequest.of(page, size, Sort.by(orders));

            Page<RealEstate> pageRealEstates;

            if (keyword == null)
                pageRealEstates = _realEstateService.findAllRealEstates(paging).getBody();
            else
                pageRealEstates = _realEstateService.listAll(keyword, paging).getBody();

            if (pageRealEstates != null)
                realEstates = pageRealEstates.getContent();
            else throw new RealEstateNotFoundException("No results found.");

            Map<String, Object> response = createResponse(realEstates, pageRealEstates);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}")
    ResponseEntity<RealEstate> findRealEstateById(@PathVariable(value = "id") Integer id) throws InvalidRequestException, RealEstateNotFoundException {
        return this._realEstateService.findRealEstateById(id);
    }

    @PostMapping(path="/add")
    ResponseEntity<RealEstate> addNewRealEstate (@RequestBody RealEstate realEstate) throws InvalidRequestException {
        return _realEstateService.saveRealEstate(realEstate);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity deleteUser(@PathVariable(value = "id") Integer id) throws InvalidRequestException, RealEstateNotFoundException {
        return this._realEstateService.deleteRealEstate(id);
    }

    @PutMapping("/update/{id}")
    ResponseEntity<RealEstate> updateUser(@PathVariable(value = "id") Integer id, @RequestBody RealEstate realEstate)
            throws InvalidRequestException, RealEstateNotFoundException {
        return this._realEstateService.updateExistingRealEstate(id, realEstate);
    }
}

