package com.example.invoiceservice.invoiceservice.DTOs;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserDTO {

    private Integer userId;
    private String FirstName;
    private String LastName;
    private String email;
    private String Password;
    private String Address;
    private String Country;
    private String City;
    private String Phone;
    private Date DateOfBirth;
    private RoleDTO Role;

    public UserDTO(String firstName, String lastName, String email, String password, String address, String country, String city, String phone, Date dateOfBirth) {
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        Password = password;
        Address = address;
        Country = country;
        City = city;
        Phone = phone;
        DateOfBirth = dateOfBirth;

    }

    public UserDTO(@NotNull(message = "User can not be null") Integer userId) {
        this.userId = userId;

    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return email;
    }
}
