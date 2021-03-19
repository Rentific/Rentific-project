package com.example.invoiceservice.invoiceservice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Invoice {
    @Id
    private Integer id;

    private Integer userId;

    private Integer realEstateId;

    public Invoice() {
    }

    public Invoice(Integer userId, Integer realEstateId) {
        this.userId = userId;
        this.realEstateId = realEstateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(Integer realEstateId) {
        this.realEstateId = realEstateId;
    }
}
