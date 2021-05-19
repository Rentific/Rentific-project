package com.example.userservice.Models;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer userId;
    @Column (nullable = false)
    @NotBlank(message = "First name is  required")
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Emailis required")
    private String email;
    @Column (nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 30, message = "Password should be between 8 and 30 characters")
    private String password;
    @Column (nullable = false)
    @NotBlank(message = "Address is required")
    private String address;
    @Column (nullable = false)
    @NotBlank(message = "Country is required")
    private String country;
    @Column (nullable = false)
    @NotBlank(message = "City is required")
    private String city;
    @Column (nullable = false)
    @NotBlank(message = "Phone is required")
    private String phone;
    @Column (nullable = false)
    private Date dateOfBirth;
    @JsonBackReference(value="name")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    //@JsonIgnore
    private Role Role;

    public User(String firstName, String lastName, String email, String password, String address, String country, String city, String phone, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
