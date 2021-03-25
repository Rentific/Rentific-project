package com.example.rentservice.rentservice.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;


@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
    @NotBlank
    private String FirstName;
    @NotBlank
    private String LastName;
    @Email(message = "Email should be valid")
    private String email;
    @Past
    private Date DateOfBirth;
    private String Phone;

    @JsonManagedReference(value="name")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<RealEstate> realEstates;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public List<RealEstate> getRealEstates() {
        return realEstates;
    }

    public void setRealEstates(List<RealEstate> realEstates) {
        this.realEstates = realEstates;
    }
    public User() {
    }

    public User(String firstName, String lastName, String email, Date dateOfBirth, String phone, List<RealEstate> realEstates) {
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        DateOfBirth = dateOfBirth;
        Phone = phone;
        this.realEstates = realEstates;
    }

}