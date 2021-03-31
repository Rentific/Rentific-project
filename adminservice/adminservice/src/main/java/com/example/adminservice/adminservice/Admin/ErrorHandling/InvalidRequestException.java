package com.example.adminservice.adminservice.Admin.ErrorHandling;


public class InvalidRequestException extends Exception {
    public InvalidRequestException(String error) {
        super(error);
    }
}
