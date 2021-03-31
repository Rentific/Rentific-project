package com.example.rentservice.rentservice.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@JsonIgnoreProperties({"HibernateLazyInitializer", "handler"})
public class State {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer StateId;
    private String Name;

    @JsonBackReference(value="name2")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "realEstateId")
    private RealEstate realEstate;

    public Integer getStateId() {
        return StateId;
    }

    public void setStateId(Integer stateId) {
        StateId = stateId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public com.example.rentservice.rentservice.Models.RealEstate getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(com.example.rentservice.rentservice.Models.RealEstate realEstate) {
        this.realEstate = realEstate;
    }

    public State() {
    }

    public State(String name, RealEstate realEstate) {
        Name = name;
        this.realEstate = realEstate;
    }

}