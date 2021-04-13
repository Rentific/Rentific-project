package com.example.rentservice.rentservice.Services;

import com.example.rentservice.rentservice.Dtos.RoleDto;
import com.example.rentservice.rentservice.Dtos.UserDto;
import com.example.rentservice.rentservice.ErrorHandling.InvalidRequestException;
import com.example.rentservice.rentservice.ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
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

    public void validateUserProperties(UserDto user) throws InvalidRequestException {
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

        if (user.getPassword() == null || user.getPassword().isEmpty()) nullProperties.add("Password");

        if (user.getAddress() == null || user.getAddress().isEmpty()) {
            nullProperties.add("Address");
        } else if (!user.getAddress().toLowerCase().matches("^[a-zA-Z0-9]+( ?[a-zA-Z0-9,.])*$")) {
            invalidProperties.add("Address");
        }
        if (user.getCountry() == null || user.getCountry().isEmpty()) {
            nullProperties.add("Country");
        } else if (!user.getCountry().toLowerCase().matches("^[a-zA-Z]+( ?[a-zA-Z])*$")) {
            invalidProperties.add("Country");
        }
        if (user.getCity() == null || user.getCity().isEmpty()) {
            nullProperties.add("City");
        } else if (!user.getCity().toLowerCase().matches("^[a-zA-Z]+( ?[a-zA-Z])*$")) {
            invalidProperties.add("City");
        }
        if (user.getPhone() == null || user.getPhone().isEmpty()) {
            nullProperties.add("Phone");
        } else if (!user.getPhone().toLowerCase().matches("[0-9]+")) {
            invalidProperties.add("Phone");
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

    public void validateRealEstate(RealEstate realEstate) throws ObjectNotFoundException {
        if(realEstate == null){
            throw new ObjectNotFoundException("Real estate is null");
        }
    }

    public void validateRoleProperties(RoleDto role) throws InvalidRequestException {
        List<String> nullProperties = new ArrayList<String>();
        List<String> invalidProperties = new ArrayList<String>();

        if (role.getName() == null || role.getName().isEmpty()) {
            nullProperties.add("Name");
        } else if (!role.getName().toLowerCase().matches("^[a-zA-Z]+( ?[a-zA-Z])*$")) {
            invalidProperties.add("Name");
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
}