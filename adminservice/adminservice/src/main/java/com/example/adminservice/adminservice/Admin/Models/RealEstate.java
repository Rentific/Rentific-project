package com.example.adminservice.adminservice.Admin.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long realEstateId;

    private String name;
    private Double price;
    private String address;
    private String country;
    private String city;
    private String description;
    private Boolean isReservated;
    private Boolean isRented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId")
    private Staff staff;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "realEstate")
    private List<State> stateList;

    public RealEstate() {
    }

    public RealEstate(
            String name,
            Double price,
            String address,
            String country,
            String city,
            String description,
            Boolean isReservated,
            Boolean isRented,
            Staff staff) {
        this.name = name;
        this.price = price;
        this.address = address;
        this.country = country;
        this.city = city;
        this.description = description;
        this.isReservated = isReservated;
        this.isRented = isRented;
        this.staff = staff;
    }

    public Long getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Long realEstateId) {
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }
}
