package com.example.rentservice.rentservice.Models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("realEstateId")
    private Integer realEstateId;
    @JsonProperty("customerId")
    private Integer customerId;
    @JsonProperty("isReserved")
    private boolean isReserved;

    public RealEstate() {
    }

    public RealEstate(Integer realEstateId, Integer customerId, boolean isReserved) {
        this.realEstateId = realEstateId;
        this.customerId = customerId;
        this.isReserved = isReserved;
    }

    public RealEstate(Integer id, Integer realEstateId, Integer customerId, boolean isReserved) {
        this.id = id;
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

    @Override
    public String toString() {
        return "RealEstate{" +
                "id=" + id +
                ", realEstateId=" + realEstateId +
                ", customerId=" + customerId +
                ", isReserved=" + isReserved +
                '}';
    }
}