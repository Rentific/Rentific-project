package com.example.rentservice.rentservice.Models;

import com.example.rentservice.rentservice.Models.RealEstate;
import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer UserId;

    private String FirstName;
    private String LastName;
    private String Email;
    private Date DateOfBirth;
    private String Phone;
    @ManyToOne
    @JoinColumn(name="real_estate_id", nullable=true)
    private RealEstate RealEstate;

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
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
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public User() {
    }

    public User(Integer userId, String firstName, String lastName, String email, Date dateOfBirth, String phone) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        DateOfBirth = dateOfBirth;
        Phone = phone;
    }
}