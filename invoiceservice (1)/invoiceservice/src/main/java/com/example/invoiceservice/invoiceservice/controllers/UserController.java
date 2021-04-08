package com.example.invoiceservice.invoiceservice.controllers;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path="/invoice-user") // This means URL's start with /demo (after Application path)
public class UserController {

    private final UserService _userService;

    public UserController(UserService userService) {
        this._userService = userService;
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    ResponseEntity<User> addNewUser(@RequestBody User user) throws InvalidRequestException, ItemNotFoundException {
        return _userService.saveUser(user);
    }

    @GetMapping(path="/all")
    ResponseEntity<List<User>> getAllUsersThatHaveInvoice() {
        return _userService.findAllUsers();
    }

    @GetMapping
    @RequestMapping("{id}")
    ResponseEntity<User> getUserById (@PathVariable Integer id) throws InvalidRequestException, ItemNotFoundException {
        return _userService.findUserById((id));
    }
}