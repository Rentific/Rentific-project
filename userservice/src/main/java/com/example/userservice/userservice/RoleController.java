package com.example.userservice.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller // This means that this class is a Controller
@RequestMapping(path="/role") // This means URL's start with /demo (after Application path)
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    Role addNewRole (@RequestBody Role role ) {
        return roleRepository.save(role);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Role> getAllRoles() {
        // This returns a JSON or XML with the users
        return roleRepository.findAll();
    }
}