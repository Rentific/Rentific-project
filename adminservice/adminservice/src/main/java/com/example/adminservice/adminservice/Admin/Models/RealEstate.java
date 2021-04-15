package com.example.adminservice.adminservice.Admin.Models;

import com.example.adminservice.adminservice.Admin.Enums.StateEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Entity
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "realEstateId")
    private Integer realEstateId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 0, message = "Price should not be less than 0")
    private Double price;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "City is mandatory")
    private String city;

    @Size(min = 10, max = 300, message = "Description must be between 10 and 300 characters")
    private String description;

    @Column(nullable = false)
    private Boolean isReservated;

    /*@JsonBackReference(value="name")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staffId")
    private StaffUser staff;*/

    @NotNull(message = "Staff cannot be null")
    private Integer staffId;

    private StateEnum state;

    public RealEstate() {
    }

    public RealEstate(String name, Double price, String address, String country, String city, String description,
                      Boolean isReservated, Integer staffId, StateEnum state) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.country = country;
        this.city = city;
        this.description = description;
        this.isReservated = isReservated;
        this.staffId = staffId;
        this.state = state;
    }

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsReservated() {
        return isReservated;
    }

    public void setIsReservated(Boolean reservated) {
        isReservated = reservated;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }
}

