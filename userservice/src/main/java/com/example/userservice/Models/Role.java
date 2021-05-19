package com.example.userservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
//@JsonIgnoreProperties({"HibernateLazyInitializer", "handler"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer roleId;
    private String name;

    @JsonManagedReference(value = "name")
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Role")
    private List<User> Users;
    public Role(Integer roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
    public Role() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
