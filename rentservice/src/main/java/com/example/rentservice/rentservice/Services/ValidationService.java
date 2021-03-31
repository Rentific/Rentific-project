package com.example.rentservice.rentservice.Services;

import ErrorHandling.InvalidRequestException;
import ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Models.User;
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
            throw new InvalidRequestException("Received Id is not valid.");
        }
    }

    public <T> void validateEmail(String email) throws InvalidRequestException {
        if (email == null || email.isEmpty()) {
            throw new InvalidRequestException("Received email is null.");
        } else if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"))  {
            throw new InvalidRequestException("Received email is not valid.");
        }
    }

    public <T> void validateObject(Optional<T> entity) throws ObjectNotFoundException {
        if (!entity.isPresent()) {
            throw new ObjectNotFoundException("Object was not found.");
        }
    }

    public void validateUserProperties(@org.jetbrains.annotations.NotNull User user) throws InvalidRequestException {
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

        if (user.getDateOfBirth() == null) {
            nullProperties.add("Birth Date");
        } else if (user.getDateOfBirth().after(new Date())) {
            invalidProperties.add("Birth date");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            nullProperties.add("Email");
        } else if (!user.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            invalidProperties.add("Email");
        }

        String result = nullProperties.size() > 0 ?
                "Properties that must be provided: " + String.join(", ", nullProperties) + ". " :
                "";

        if (invalidProperties.size() > 0) {
            result += "Wrong format: " + String.join(", ", invalidProperties) + ".";
        }

        if (!result.isEmpty()) {
            throw new InvalidRequestException(result);
        }
    }

    public void validateRealEstateProperties(@org.jetbrains.annotations.NotNull RealEstate realEstate) throws InvalidRequestException {
        return;
    }
}