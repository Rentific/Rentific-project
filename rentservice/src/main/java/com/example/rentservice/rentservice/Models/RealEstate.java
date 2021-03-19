package com.example.rentservice.rentservice.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class RealEstate {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer RealEstateId;

    private String Name;
    private Double Price;
    private String Address;
    private String Country;
    private String City;
    private String Description;
    private boolean IsReservated;
    private Date DateFrom;
    private Date DateTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "RealEstate")
    private List<User> Users;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "RealEstate")
    private List<State> States;

    public Integer getRealEstateId() {
        return RealEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        RealEstateId = realEstateId;
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

    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }

    public List<State> getStates() {
        return States;
    }

    public void setStates(List<State> states) {
        States = states;
    }

    public RealEstate() {
    }

    public RealEstate(Integer realEstateId, String name, Double price, String address, String country, String city, String description, boolean isReservated, Date dateFrom, Date dateTo) {
        RealEstateId = realEstateId;
        Name = name;
        Price = price;
        Address = address;
        Country = country;
        City = city;
        Description = description;
        IsReservated = isReservated;
        DateFrom = dateFrom;
        DateTo = dateTo;
    }

}