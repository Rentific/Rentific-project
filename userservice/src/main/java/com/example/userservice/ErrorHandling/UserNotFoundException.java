package com.example.userservice.ErrorHandling;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String error) {

        super(error);
    }
}