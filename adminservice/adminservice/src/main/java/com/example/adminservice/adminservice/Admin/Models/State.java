package com.example.adminservice.adminservice.Admin.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stateId")
    private Integer stateId;

    @Column(nullable = false)
    private String name;

    @JsonBackReference(value="name2")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "realEstateId")
    private RealEstate realEstate;

    public State() {
    }

    public State(String name, RealEstate realEstate) {
        this.name = name;
        this.realEstate = realEstate;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(RealEstate realEstate) {
        this.realEstate = realEstate;
    }
}
