package com.example.invoiceservice.invoiceservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@JsonIgnoreProperties({"HibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @NotNull
    private Integer userId;

    @NotBlank(message = "Name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Invoice> invoices;

    public User() {
    }
    public User(Integer id) {this.userId = id; }

    public User( String firstName, String lastName, List<Invoice> invoices) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.invoices = invoices;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}