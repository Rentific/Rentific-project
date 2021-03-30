package com.example.adminservice.adminservice.Admin.Controllers;

import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Services.RealEstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/real-estate")
public class RealEstateController {
    @Autowired
    private final RealEstateService _realEstateService;

    public RealEstateController(RealEstateService realEstateService) {
        _realEstateService = realEstateService;
    }

    @GetMapping(path="/all/real-estates")
    ResponseEntity<List<RealEstate>> findAllRealEstates() {
        return _realEstateService.findAllRealEstates();
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


