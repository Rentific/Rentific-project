package com.example.adminservice.adminservice.Admin.Models;

import javax.persistence.*;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stateId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "realEstateId")
    private RealEstate realEstate;

    public State() {
    }

    public State(Long stateId, String name) {
        this.stateId = stateId;
        this.name = name;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
