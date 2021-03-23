package com.example.adminservice.adminservice.Admin.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(nullable = false)
    private Boolean isRented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId")
    private StaffUser staff;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "realEstate")
    private List<State> stateList;

    public RealEstate() {
    }

    public RealEstate(String name, Double price, String address, String country, String city, String description,
                      Boolean isReservated, Boolean isRented, StaffUser staff, List<State> stateList) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.country = country;
        this.city = city;
        this.description = description;
        this.isReservated = isReservated;
        this.isRented = isRented;
        this.staff = staff;
        this.stateList = stateList;
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

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean rented) {
        isRented = rented;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }
}

