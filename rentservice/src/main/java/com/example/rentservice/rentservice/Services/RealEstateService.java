package com.example.rentservice.rentservice.Services;

import com.example.rentservice.rentservice.ErrorHandling.InvalidRequestException;
import com.example.rentservice.rentservice.ErrorHandling.ObjectNotFoundException;
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

<<<<<<< HEAD
    public ResponseEntity<List<RealEstate>> findAllReservatedRealEstates() {
        return new ResponseEntity(_realEstateRepository.findByIsReservatedTrue(), HttpStatus.OK);
=======
    public ResponseEntity<List<RealEstate>> findAllReservedRealEstates() {
        return new ResponseEntity(_realEstateRepository.findByIsReservedTrue(), HttpStatus.OK);
>>>>>>> 18c9e1ddfbfdead1c301a61a13576df62685f594
    }

    public ResponseEntity<RealEstate> findRealEstateById(Integer id) throws InvalidRequestException, ObjectNotFoundException {
        this._validationService.validateId(id);

        Optional<RealEstate> realEstate = _realEstateRepository.findById(id);
        if(realEstate.isPresent()){
            return new ResponseEntity(realEstate.get(), HttpStatus.OK);
        }
        else{
            throw new ObjectNotFoundException("There is no real estate with the ID "+id);
        }
    }

    public ResponseEntity<RealEstate> saveRealEstate(RealEstate realEstate) throws InvalidRequestException {
        try{
            this._validationService.validateRealEstate(realEstate);
            RealEstate newRealEstate = this._realEstateRepository.save(realEstate);
            return new ResponseEntity(newRealEstate, HttpStatus.OK);
        }
        catch(ObjectNotFoundException ex){
            return new ResponseEntity("Fail to save real estate. Message: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<RealEstate> reserveRealEstate(Integer id) throws InvalidRequestException, ObjectNotFoundException {
        try
        {
            this._validationService.validateId(id);

            var realEstate = this.findRealEstateById(id).getBody();

<<<<<<< HEAD
            realEstate.setIsReservated(true);
=======
            realEstate.setIsReserved(true);
>>>>>>> 18c9e1ddfbfdead1c301a61a13576df62685f594
            RealEstate updatedRealEstate = this._realEstateRepository.save(realEstate);
            return new ResponseEntity(updatedRealEstate, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity("Fail to update real estate. Message: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity deleteRealEstate(Integer id) throws InvalidRequestException, ObjectNotFoundException {

        try{
            this._validationService.validateId(id);

            this.findRealEstateById(id);

            this._realEstateRepository.deleteById(id);
            return new ResponseEntity("Real estate successfully deleted.", HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity("Fail to delete real estate. Message: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}