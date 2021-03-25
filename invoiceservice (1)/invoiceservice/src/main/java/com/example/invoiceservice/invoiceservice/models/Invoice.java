package com.example.invoiceservice.invoiceservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"HibernateLazyInitializer", "handler"})
public class Invoice {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private Integer invoiceId;

    @Column(nullable = false)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date invoiceDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "real_estate_id")
    private RealEstate realEstate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Invoice() {
    }

    public Invoice(Integer id, Date invoiceDate, RealEstate realEstate, User user) {
        this.invoiceId = id;
        this.invoiceDate = invoiceDate;
        this.realEstate = realEstate;
        this.user = user;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
