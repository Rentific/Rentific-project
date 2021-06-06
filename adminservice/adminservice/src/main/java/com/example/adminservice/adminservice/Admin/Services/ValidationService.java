package com.example.adminservice.adminservice.Admin.Services;

import com.example.adminservice.adminservice.Admin.ErrorHandling.InvalidRequestException;
import com.example.adminservice.adminservice.Admin.ErrorHandling.RealEstateNotFoundException;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValidationService {
    public ValidationService() {
    }

    public void validateId(Integer id) throws InvalidRequestException {
        if (id == null || id < 1) {
            throw new InvalidRequestException("Received Id is not valid.");
        }
    }

    public <T> void validateRealEstate(Optional<T> entity) throws RealEstateNotFoundException {
        if (!entity.isPresent()) {
            throw new RealEstateNotFoundException("Real estate with received Id was not found.");
        }
    }

    public void validateRealEstateProperties(RealEstate realEstate) throws InvalidRequestException {
        List<String> nullProperties = new ArrayList<String>();
        List<String> invalidProperties = new ArrayList<String>();

        // Name
        if (realEstate.getName() == null || realEstate.getName().isEmpty()) {
            nullProperties.add("Name");
        }
        else if (!realEstate.getName().toLowerCase().matches("^[a-žA-Ž0-9]+( ?[a-žA-Ž0-9,])*$")) {
            invalidProperties.add("Name");
        }

        // Price
        if (realEstate.getPrice() == null) {
            nullProperties.add("Price");
        }
        else if (realEstate.getPrice() < 0) {
            invalidProperties.add("Price");
        }

        // Address
        if (realEstate.getAddress() == null || realEstate.getAddress().isEmpty()) {
            nullProperties.add("Address");
        }
        else if (!realEstate.getAddress().toLowerCase().matches("^[a-ŽA-Ž0-9]+( ?[a-žA-Ž0-9,.])*$")) {
            invalidProperties.add("Address");
        }

        // Country
        if (realEstate.getCountry() == null || realEstate.getCountry().isEmpty()) {
            nullProperties.add("Country");
        }
        else if (!realEstate.getCountry().toLowerCase().matches("^[a-žA-Ž0-9]+( ?[a-žA-Ž0-9,.-])*$")) {
            invalidProperties.add("Country");
        }

        // City
        if (realEstate.getCity() == null || realEstate.getCity().isEmpty()) {
            nullProperties.add("City");
        }
        else if (!realEstate.getCity().toLowerCase().matches("^[a-žA-Ž0-9]+( ?[a-žA-Ž0-9,.-])*$")) {
            invalidProperties.add("City");
        }

        // Description
        if (realEstate.getDescription() == null || realEstate.getDescription().isEmpty()) {
            nullProperties.add("Description");
        }
        else if (!realEstate.getDescription().toLowerCase().matches("^[a-žA-Ž0-9]+( ?[a-žA-Ž0-9-:-?{-~!\"^_`\\[\\].,!;])*$")) {
            invalidProperties.add("Description");
        }

        // Staff
        if (realEstate.getStaffId() == null) {
            nullProperties.add("Staff");
        }
        /*else if (!realEstate.getStaff().getFirstName().toLowerCase().matches("^[a-zA-Z0-9]+( ?[a-zA-Z0-9])*$") ||
                !realEstate.getStaff().getLastName().toLowerCase().matches("^[a-zA-Z0-9]+( ?[a-zA-Z0-9])*$")) {
            invalidProperties.add("Staff");
        }*/

        String result = nullProperties.size() > 0 ?
                "Please, populate following properties: " + String.join(", ", nullProperties) + ". " :
                "";

        if (invalidProperties.size() > 0) {
            result += "Wrong format of following properties: " + String.join(", ", invalidProperties) + ".";
        }

        if (!result.isEmpty()) {
            throw new InvalidRequestException(result);
        }
    }
}
