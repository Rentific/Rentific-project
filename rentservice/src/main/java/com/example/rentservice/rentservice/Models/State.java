package com.example.rentservice.rentservice.Models;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class State {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer StateId;
    private String Name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id")
    private RealEstate RealEstate;

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
        return RealEstate;
    }

    public void setRealEstate(com.example.rentservice.rentservice.Models.RealEstate realEstate) {
        RealEstate = realEstate;
    }

    public State() {
    }

    public State(Integer stateId, String name) {
        StateId = stateId;
        Name = name;
    }

}