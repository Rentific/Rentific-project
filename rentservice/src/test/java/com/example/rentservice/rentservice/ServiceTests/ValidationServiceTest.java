package com.example.rentservice.rentservice.ServiceTests;

import com.example.rentservice.rentservice.Dtos.UserDto;
import com.example.rentservice.rentservice.ErrorHandling.InvalidRequestException;
import com.example.rentservice.rentservice.ErrorHandling.ObjectNotFoundException;
import com.example.rentservice.rentservice.Models.RealEstate;
import com.example.rentservice.rentservice.Services.ValidationService;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationServiceTest {

    private ValidationService _validationService;

    ValidationServiceTest() {
        _validationService = new ValidationService();
    }

    @Test
    public void validateId_ShouldThrowInvalidRequestException() {
        Exception exception = assertThrows(InvalidRequestException.class, () -> {
            _validationService.validateId(0);
        });

        String expectedMessage = "Received Id is not valid.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validateId_ShouldNotThrowException_WhenIdIsValid() {
        assertDoesNotThrow(() -> {
            _validationService.validateId(2);
        });
    }

    @Test
    public void validateObject_ShouldThrowUserNotFoundException() {
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            _validationService.validateObject(Optional.empty());
        });
        String expectedMessage = "Object was not found.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validateObject_ShouldNotThrowException_WhenObjectIsNotNull() {
        RealEstate realEstate = new RealEstate(1,null, false );
        assertDoesNotThrow(() -> {
            _validationService.validateObject(Optional.of(realEstate));
        });
    }

    //First case - missing required properties (e.g. first and last name, email)
    @Test
    public void validateUserProperties_ShouldThrowInvalidRequestException_1() {
        UserDto user = new UserDto("", "", " ", "Password123!", "address", "country", "city", "123456",  new Date());

        Exception exception = assertThrows(InvalidRequestException.class, () -> {
            _validationService.validateUserProperties(user);
        });

        String expectedMessage = "Properties that must be provided: First Name, Last Name. Wrong format: Email.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    //Second case - wrong format
    @Test
    public void validateUserProperties_ShouldThrowInvalidRequestException_2() {
        UserDto user =  new UserDto("TestName", "TestSurname", "1", "Password123!","Adresa1", "BIH", "Sarajevo", "test",new Date());

        Exception exception = assertThrows(InvalidRequestException.class, () -> {
            _validationService.validateUserProperties(user);
        });
        String expectedMessage = "Wrong format: Email, Phone.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void validateUserProperties_ShouldNotThrowException_WhenUserIsValid() {
        UserDto user = new UserDto("Test", "Test", "noviemail@email.com", "Password123!","Adresa1", "BIH", "Sarajevo", "123456",new Date());

        assertDoesNotThrow(() -> {
            _validationService.validateUserProperties(user);
        });
    }
}