package com.example.adminservice.adminservice.Admin.Controllers;

import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Models.StaffUser;
import com.example.adminservice.adminservice.Admin.Repositories.RealEstateRepository;
import com.example.adminservice.adminservice.Admin.Repositories.StaffUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/staff-user")
public class StaffUserController {
    @Autowired
    private StaffUserRepository staffUserRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    StaffUser addNewRealEstate (@RequestBody StaffUser staffUser) {
        return staffUserRepository.save(staffUser);
    }

    @GetMapping(path="/all/staff-users")
    public @ResponseBody Iterable<StaffUser> getAllUsers() {
        return staffUserRepository.findAll();
    }
}