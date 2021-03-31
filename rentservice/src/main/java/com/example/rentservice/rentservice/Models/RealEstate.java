package com.example.rentservice.rentservice.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class RealEstate {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer realEstateId;
    @NotBlank
    private String Name;
    private Double Price;
    private String Address;
    private String Country;
    private String City;
    private String Description;
    private boolean IsReservated;
    private Date DateFrom;
    @FutureOrPresent
    private Date DateTo;

    @JsonBackReference(value="name")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    @JsonManagedReference(value="name2")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "realEstate")
    private List<State> states;

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean getIsReservated() {
        return IsReservated;
    }

    public void setIsReservated(boolean isReservated) {
        IsReservated = isReservated;
    }

    public Date getDateFrom() {
        return DateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        DateFrom = dateFrom;
    }

    public Date getDateTo() {
        return DateTo;
    }

    public void setDateTo(Date dateTo) {
        DateTo = dateTo;
    }

    public com.example.rentservice.rentservice.Models.User getUser() {
        return user;
    }

    public void setUser(com.example.rentservice.rentservice.Models.User user) {
        this.user = user;
    }
    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public RealEstate() {
    }

    public RealEstate(String name, Double price, String address, String country, String city, String description, boolean isReservated, Date dateFrom, Date dateTo, User user) {
        Name = name;
        Price = price;
        Address = address;
        Country = country;
        City = city;
        Description = description;
        IsReservated = isReservated;
        DateFrom = dateFrom;
        DateTo = dateTo;
        this.user = user;
    }

}