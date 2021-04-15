package com.example.invoiceservice.invoiceservice.services;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.RealEstate;

import com.example.invoiceservice.invoiceservice.models.User;

import com.example.invoiceservice.invoiceservice.repositories.RealEstateRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateService {
    private final RealEstateRepository _realEstateRepository;
    private final ValidationService _validationService;

    public RealEstateService(RealEstateRepository realEstateRepository, ValidationService _validationService) {
        this._realEstateRepository = realEstateRepository;
        this._validationService = _validationService;
    }

    public ResponseEntity<List<RealEstate>> findAllRealEstates() {
        return new ResponseEntity<List<RealEstate>>(this._realEstateRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<RealEstate> findRealEstateById(Integer id) throws InvalidRequestException, ItemNotFoundException {
        this._validationService.validateId(id);

        Optional<RealEstate> realEstate = this._realEstateRepository.findById(id);
        this._validationService.validateItem(realEstate);
        return new ResponseEntity(realEstate.get(), HttpStatus.OK);
    }

    public ResponseEntity<RealEstate> saveRealEstate(RealEstate realEstate) throws InvalidRequestException {
        this._validationService.validateRealEstateProperties(realEstate);
        RealEstate newRealEstate = this._realEstateRepository.save(realEstate);
        return new ResponseEntity(newRealEstate, HttpStatus.OK);
    }


    public ResponseEntity<RealEstate> findRealEstateByRealEstateName(String name) throws ItemNotFoundException{
        Optional<RealEstate> realEstate = this._realEstateRepository.findRealEstateByName(name);
        this._validationService.validateItem(realEstate);
        return new ResponseEntity(realEstate.get(), HttpStatus.OK);
    }

}
