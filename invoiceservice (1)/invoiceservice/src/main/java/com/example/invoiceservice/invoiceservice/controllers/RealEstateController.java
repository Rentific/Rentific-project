package com.example.invoiceservice.invoiceservice.controllers;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.RealEstate;
import com.example.invoiceservice.invoiceservice.services.RealEstateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // This means that this class is a Controller
@RequestMapping(path="/invoice-realEstate") // This means URL's start with /invoice (after Application path)
public class RealEstateController {

    private RealEstateService _realEstateService;

    public RealEstateController(RealEstateService realEstateService){
        this._realEstateService = realEstateService;
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    ResponseEntity<RealEstate> addRealEstateForInvoice (@RequestBody RealEstate realEstate)  throws InvalidRequestException {
       return _realEstateService.saveRealEstate(realEstate);
    }

    @GetMapping(path="/all")
    ResponseEntity<List<RealEstate>> getAllRealEstatesWithInvoice() {
        return _realEstateService.findAllRealEstates();
    }

    @GetMapping
    @RequestMapping("{id}")
    ResponseEntity<RealEstate> getRealEstateById(@PathVariable Integer id)  throws InvalidRequestException, ItemNotFoundException {
        return _realEstateService.findRealEstateById(id);
    }
}