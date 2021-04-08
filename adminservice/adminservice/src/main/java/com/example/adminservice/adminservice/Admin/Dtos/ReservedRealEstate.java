package com.example.adminservice.adminservice.Admin.Dtos;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ReservedRealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservedRealEstateId")
    private Integer reservedRealEstateId;

    private Integer realEstateId;

    private Integer customerId;

    private boolean isReserved;

    public ReservedRealEstate() {
    }

    public ReservedRealEstate(Integer realEstateId, Integer customerId, boolean isReserved) {
        this.realEstateId = realEstateId;
        this.customerId = customerId;
        this.isReserved = isReserved;
    }

    public Integer getReservedRealEstateId() {
        return reservedRealEstateId;
    }

    public void setReservedRealEstateId(Integer reservedRealEstateId) {
        this.reservedRealEstateId = reservedRealEstateId;
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

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
