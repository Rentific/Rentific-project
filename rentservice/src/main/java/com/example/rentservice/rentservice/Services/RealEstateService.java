package com.example.rentservice.rentservice.Services;

import ErrorHandling.InvalidRequestException;
import ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Repositories.RealEstateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealEstateService {
    //final - can't be extended
    private final RealEstateRepository _realEstateRepository;
    private final ValidationService _validationService;

    public RealEstateService(RealEstateRepository realEstateRepository, ValidationService _validationService) {
        this._realEstateRepository = realEstateRepository;
        this._validationService = _validationService;
    }

    public ResponseEntity<List<RealEstate>> findAllRealEstates() {
        return new ResponseEntity(_realEstateRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<RealEstate> findRealEstateById(Integer id) throws InvalidRequestException, ObjectNotFoundException {
        this._validationService.validateId(id);

        Optional<RealEstate> realEstate = _realEstateRepository.findById(id);
        this._validationService.validateObject(realEstate);
        return new ResponseEntity(realEstate.get(), HttpStatus.OK);
    }

    public ResponseEntity<RealEstate> saveRealEstate(RealEstate realEstate) throws InvalidRequestException, ObjectNotFoundException {
        try {
            findRealEstateById(realEstate.getRealEstateId());
            return new ResponseEntity("Real estate already exists.", HttpStatus.CONFLICT);
        } 
        catch (Exception e) {
            this._validationService.validateRealEstateProperties(realEstate);
            RealEstate newRE = this._realEstateRepository.save(realEstate);
            return new ResponseEntity(newRE, HttpStatus.OK);
        }
    }

    public ResponseEntity<RealEstate> updateExistingRealEstate(Integer id, RealEstate realEstate) throws InvalidRequestException, ObjectNotFoundException {
        this._validationService.validateId(id);

        this.findRealEstateById(id);

        realEstate.setRealEstateId(id);
        RealEstate updatedRealEstate = this._realEstateRepository.save(realEstate);
        return new ResponseEntity(updatedRealEstate, HttpStatus.OK);
    }

    public ResponseEntity deleteRealEstate(Integer id) throws InvalidRequestException, ObjectNotFoundException {
        this._validationService.validateId(id);

        this.findRealEstateById(id);

        this._realEstateRepository.deleteById(id);
        return new ResponseEntity("User successfully deleted.", HttpStatus.OK);
    }
}