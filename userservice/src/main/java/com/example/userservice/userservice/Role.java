package com.example.userservice.userservice;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private Integer roleId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "Role")
    private List<User> Users;
    public Role(Integer id, String name) {
        this.roleId = id;
        this.name = name;
    }
    public Role() {
    }
    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }

    public Integer getId() {
        return roleId;
    }

    public void setId(Integer id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }

}
