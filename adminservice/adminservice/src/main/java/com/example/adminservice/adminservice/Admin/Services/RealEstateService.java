package com.example.adminservice.adminservice.Admin.Services;

import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Repositories.RealEstateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateService {
    private final RealEstateRepository _realEstateRepository;
    private final ValidationService _validationService;

    public RealEstateService(RealEstateRepository realEstateRepository, ValidationService validationService) {
        _realEstateRepository = realEstateRepository;
        _validationService = validationService;
    }

    public ResponseEntity<List<RealEstate>> findAllRealEstates() {
        return new ResponseEntity<>(this._realEstateRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<RealEstate> findRealEstateById(Integer id) throws InvalidRequestException, RealEstateNotFoundException {
        this._validationService.validateId(id);

        Optional<RealEstate> realEstate = this._realEstateRepository.findById(id);
        this._validationService.validateRealEstate(realEstate);
        return new ResponseEntity(realEstate.get(), HttpStatus.OK);
    }

    public ResponseEntity<RealEstate> saveRealEstate(RealEstate realEstate) throws InvalidRequestException {
        this._validationService.validateRealEstateProperties(realEstate);
        RealEstate newRealEstate = this._realEstateRepository.save(realEstate);
        return new ResponseEntity(newRealEstate, HttpStatus.OK);
    }

    public ResponseEntity deleteRealEstate(Integer id) throws InvalidRequestException, RealEstateNotFoundException {
        this._validationService.validateId(id);
        this.findRealEstateById(id);
        this._realEstateRepository.deleteById(id);
        return new ResponseEntity("Real estate successfully deleted.", HttpStatus.OK);
    }
}
