package com.example.invoiceservice.invoiceservice.ExceptionHandler;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String error) {
        super(error);
    }
}
