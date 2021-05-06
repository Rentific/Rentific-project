package com.example.authenticationservice.Model;


public class Role {
    private Integer RoleId;
    private String Name;

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
}
