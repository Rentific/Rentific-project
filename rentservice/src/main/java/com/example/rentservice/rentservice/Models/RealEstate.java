package com.example.rentservice.rentservice.Models;


import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer realEstateId;

    private Integer customerId;

    private boolean isReserved;

    public RealEstate() {
    }

    public RealEstate(Integer realEstateId, Integer customerId, boolean isReserved) {
        this.realEstateId = realEstateId;
        this.customerId = customerId;
        this.isReserved = isReserved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(boolean reserved) {
        isReserved = reserved;
    }

}