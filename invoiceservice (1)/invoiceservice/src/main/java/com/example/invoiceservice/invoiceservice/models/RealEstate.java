package com.example.invoiceservice.invoiceservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RealEstate {
    @Id
    @Column(unique = true)
    @NotNull
    private Integer realEstateId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    private Double price;

    @OneToOne(mappedBy = "realEstate")
    private Invoice invoice;

    public RealEstate() {
    }
    public RealEstate(Integer id) {this.realEstateId = id; }

    public RealEstate(String name, Double price, Invoice invoice) {
        this.name = name;
        this.price = price;
        this.invoice = invoice;
    }

    public RealEstate(String name, Double price) {
        this.name = name;
        this.price = price;
        this.invoice = null;
    }

    public RealEstate(Integer realEstateId, @NotBlank(message = "Name is mandatory") String name, Double price) {
        this.realEstateId = realEstateId;
        this.name = name;
        this.price = price;
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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
