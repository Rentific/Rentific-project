package com.example.invoiceservice.invoiceservice.services;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository _userRepository;
    private final ValidationService _validationService;

    public UserService(UserRepository userRepository, ValidationService _validationService) {
        this._userRepository = userRepository;
        this._validationService = _validationService;
    }

    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<List<User>>(this._userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<User> findUserById(Integer id) throws InvalidRequestException, ItemNotFoundException{
        this._validationService.validateId(id);

        Optional<User> user = this._userRepository.findById(id);
        this._validationService.validateItem(user);
        return new ResponseEntity(user.get(), HttpStatus.OK);
    }


    public ResponseEntity<User> saveUser(User user) throws InvalidRequestException, ItemNotFoundException {

            this._validationService.validateUserProperties(user);
            User newUser = this._userRepository.save(user);
            return new ResponseEntity(newUser, HttpStatus.OK);
    }


    public ResponseEntity<User> findUserByEmail(String email) throws ItemNotFoundException{
        Optional<User> user = this._userRepository.findByEmail(email);
        this._validationService.validateItem(user);
        return new ResponseEntity(user.get(), HttpStatus.OK);
    }


}
