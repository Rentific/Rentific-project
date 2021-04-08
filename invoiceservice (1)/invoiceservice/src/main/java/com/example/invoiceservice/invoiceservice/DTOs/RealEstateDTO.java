package com.example.invoiceservice.invoiceservice.DTOs;

public class RealEstateDTO {
    private Integer realEstateId;
    private String name;
    private Double price;
    private String address;
    private String country;
    private String city;
    private String description;
    private Boolean isReservated;
    private Boolean isRented;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
