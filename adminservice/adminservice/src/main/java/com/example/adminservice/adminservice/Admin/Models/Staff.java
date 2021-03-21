package com.example.adminservice.adminservice.Admin.Models;

import java.util.List;
import javax.persistence.*;


@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long staffId;

    private String firstName;
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "staff")
    private List<RealEstate> realEstateList;

    public Staff() {
    }

    public Staff(Long staffId, String firstName, String lastName) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
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
