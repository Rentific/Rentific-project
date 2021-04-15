package com.example.adminservice.adminservice.Admin.Dtos;


import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class ReservedRealEstate {
    @Id
    private Integer id;

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