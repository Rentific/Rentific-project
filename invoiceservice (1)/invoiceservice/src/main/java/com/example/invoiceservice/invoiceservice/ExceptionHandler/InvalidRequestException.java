package com.example.invoiceservice.invoiceservice.ExceptionHandler;

public class InvalidRequestException extends Exception {
    public InvalidRequestException(String error) {
        super(error);
    }
}
