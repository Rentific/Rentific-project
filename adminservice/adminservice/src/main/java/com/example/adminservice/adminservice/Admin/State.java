package com.example.adminservice.adminservice.Admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer stateId;
    private String name;

    public State() {
    }

    public State(Integer stateId, String name) {
        this.stateId = stateId;
        this.name = name;
    }

    @Id
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
}
