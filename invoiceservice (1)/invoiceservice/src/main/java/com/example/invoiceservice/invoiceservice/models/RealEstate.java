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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private Integer realEstateId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "realEstate")
    @JsonIgnore
    private List<Invoice> invoices;

    public RealEstate() {
    }
    public RealEstate(Integer id) {this.realEstateId = id; }

    public RealEstate(String name, Double price, List<Invoice> invoices) {
        this.name = name;
        this.price = price;
        this.invoices = invoices;
    }

    public RealEstate(String name, Double price) {
        this.name = name;
        this.price = price;
        this.invoices = null;
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

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
