package com.example.invoiceservice.invoiceservice.services;

import com.example.invoiceservice.invoiceservice.models.User;
import com.example.invoiceservice.invoiceservice.models.Invoice;
import com.example.invoiceservice.invoiceservice.models.RealEstate;

import com.example.invoiceservice.invoiceservice.ExceptionHandler.InvalidRequestException;
import com.example.invoiceservice.invoiceservice.ExceptionHandler.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {
    public ValidationService() {
    }

    public void validateId(Integer id) throws InvalidRequestException {
        if (id == null || id < 1) {
            throw new InvalidRequestException("Sent Id is not valid.");
        }
    }

    public <T> void validateItem(Optional<T> entity) throws ItemNotFoundException {
        if (!entity.isPresent()) {
            throw new ItemNotFoundException("Object with sent ID is not found.");
        }
    }

    public void validateUserProperties(User user) throws InvalidRequestException {
        List<String> nullProperties = new ArrayList<String>();
        List<String> invalidProperties = new ArrayList<String>();

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            nullProperties.add("First Name");
        } else if (!user.getFirstName().toLowerCase().matches("^[a-zA-Z]+( ?[a-zA-Z])*$")) {
            invalidProperties.add("First Name");
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            nullProperties.add("Last Name");
        } else if (!user.getLastName().matches("^[a-zA-Z]+( ?[a-zA-Z])*$")) {
            invalidProperties.add("Last Name");
        }

        String resultForUser = nullProperties.size() > 0 ?
                "Properties that must be provided with data: " + String.join(", ", nullProperties) + ". " :
                "";

        if (invalidProperties.size() > 0) {
            resultForUser += "Wrong format of properties: " + String.join(", ", invalidProperties) + ".";
        }

        if (!resultForUser.isEmpty()) {
            throw new InvalidRequestException(resultForUser);
        }
    }

    public void validateInvoiceProperties(Invoice invoice) throws InvalidRequestException {
        List<String> nullProperties = new ArrayList<String>();
        List<String> invalidProperties = new ArrayList<String>();

        if (invoice.getInvoiceDate() == null) {
            nullProperties.add("Invoice date");
        } else if (!invoice.getInvoiceDate().before(new Date())) {
            invalidProperties.add("Invoice date");
        }

        String resultForInvoice = nullProperties.size() > 0 ?
                "Properties that must be provided with data: " + String.join(", ", nullProperties) + ". " :
                "";

        if (invalidProperties.size() > 0) {
            resultForInvoice += "Wrong format of properties: " + String.join(", ", invalidProperties) + ".";
        }

        if (!resultForInvoice.isEmpty()) {
            throw new InvalidRequestException(resultForInvoice);
        }
    }

    public void validateRealEstateProperties(RealEstate realEstate) throws InvalidRequestException {
        List<String> nullProperties = new ArrayList<String>();
        List<String> invalidProperties = new ArrayList<String>();

        if (realEstate.getName() == null || realEstate.getName().isEmpty()) {
            nullProperties.add("Name");
        } else if (!realEstate.getName().toLowerCase().matches("^[a-zA-Z]+( ?[a-zA-Z])*$")) {
            invalidProperties.add("Name");
        }


        if (realEstate.getPrice() == null) {
            nullProperties.add("Real estate price");
        } else if (realEstate.getPrice() < 0) {
            invalidProperties.add("Real estate price");
        }

        String resultForRealEstate = nullProperties.size() > 0 ?
                "Properties that must be provided with data: " + String.join(", ", nullProperties) + ". " :
                "";

        if (invalidProperties.size() > 0) {
            resultForRealEstate += "Invalid data: " + String.join(", ", invalidProperties) + ".";
        }

        if (!resultForRealEstate.isEmpty()) {
            throw new InvalidRequestException(resultForRealEstate);
        }
    }
}