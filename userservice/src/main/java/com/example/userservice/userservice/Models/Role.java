package com.example.userservice.userservice.Models;

import com.example.userservice.userservice.Models.User;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @NotNull
    private Integer RoleId;
    private String Name;
   //@OneToMany(fetch = FetchType.LAZY, mappedBy = "Role")
    //private List<User> Users;
    public Role(Integer roleId, String name) {
        RoleId = roleId;
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

   /* public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> users) {
        Users = users;
    }*/

}
