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
    private Integer RoleId;
    private String Name;

    @JsonManagedReference(value = "name")
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Role")
    private List<User> Users;
    public Role(String name) {
        Name = name;
    }
    public Role() {
    }

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
