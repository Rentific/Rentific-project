package com.example.invoiceservice.invoiceservice.DTOs;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserDTO {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String country;
    private String city;
    private String phone;
    private Date DateOfBirth;
   private RoleDTO Role;


    public UserDTO() {
    }

    public UserDTO(@NotNull(message = "User can not be null") Integer userId) {
        this.userId = userId;

    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return email;
    }
}
