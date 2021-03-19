package com.example.rentservice.rentservice.Models;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class State {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer StateId;
    private String Name;
    @ManyToOne
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

    public State() {
    }

    public State(Integer stateId, String name) {
        StateId = stateId;
        Name = name;
    }
}