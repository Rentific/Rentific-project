package com.example.adminservice.adminservice.Admin.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class StaffUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staffId")
    private Integer staffId;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    @JsonManagedReference(value = "name")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "staff")
    private List<RealEstate> realEstateList;

    public StaffUser() {
    }

    public StaffUser(String firstName, String lastName, List<RealEstate> realEstateList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.realEstateList = realEstateList;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
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

    public List<RealEstate> getRealEstateList() {
        return realEstateList;
    }

    public void setRealEstateList(List<RealEstate> realEstateList) {
        this.realEstateList = realEstateList;
    }
}
