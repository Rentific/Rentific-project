package com.example.rentservice.rentservice.Dtos;

import java.util.List;

public class RoleDto {
    private Integer RoleId;
    private String Name;
    private List<UserDto> Users;

    public RoleDto(Integer roleId, String name) {
        RoleId = roleId;
        Name = name;
    }
    public RoleDto() {
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