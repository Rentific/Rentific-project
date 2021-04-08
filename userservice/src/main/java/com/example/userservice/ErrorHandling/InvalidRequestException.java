package com.example.userservice.ErrorHandling;

public class InvalidRequestException extends Exception {
        public InvalidRequestException(String error) {

            super(error);
        }
    }


