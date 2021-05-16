package com.example.rentservice.rentservice.Controllers;

import com.example.rentservice.rentservice.ErrorHandling.InvalidRequestException;
import com.example.rentservice.rentservice.ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Services.RealEstateService;
import com.example.rentservice.rentservice.rabbitmq.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/realEstate") // This means URL's start with /demo (after Application path)
public class RealEstateController {
    private RealEstateService _realEstateService;
    private final Sender sender;

    public RealEstateController(RealEstateService realEstateService, Sender sender){
        _realEstateService = realEstateService;
        this.sender = sender;
    }

    @GetMapping(path="/all")
    ResponseEntity<List<RealEstate>> getAllRealEstates() {
        // This returns a JSON or XML with the RealEstates
        sender.send(0);
        return _realEstateService.findAllRealEstates();
    }

    @GetMapping(path="/allReserved")
    ResponseEntity<List<RealEstate>> getAllReservedRealEstates() {
        return _realEstateService.findAllReservedRealEstates();
    }
  
    @GetMapping("/{id}")
    ResponseEntity<RealEstate> findRealEstateById(@PathVariable(value = "id") Integer id) throws InvalidRequestException, ObjectNotFoundException {
        return _realEstateService.findRealEstateById(id);
    }

    @PostMapping("/add")
    ResponseEntity<RealEstate> addNewRealEstate(@RequestBody RealEstate RealEstate) throws InvalidRequestException, ObjectNotFoundException {
        return _realEstateService.saveRealEstate(RealEstate);
    }

    @PutMapping("/reserve/{id}")
    ResponseEntity<RealEstate> reserveRealEstate(@PathVariable(value = "id") Integer id) throws InvalidRequestException, ObjectNotFoundException {
        return _realEstateService.reserveRealEstate(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity deleteRealEstate(@PathVariable(value = "id") Integer id) throws InvalidRequestException, ObjectNotFoundException {

        return _realEstateService.deleteRealEstate(id);
    }
}