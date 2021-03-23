package com.example.adminservice.adminservice.Admin.ErrorHandling;

public class RealEstateNotFoundException extends Exception {
    public RealEstateNotFoundException(String error) {
        super(error);
    }
}
