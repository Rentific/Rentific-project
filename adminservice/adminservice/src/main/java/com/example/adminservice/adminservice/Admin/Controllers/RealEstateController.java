package com.example.adminservice.adminservice.Admin.Controllers;

import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import com.example.adminservice.adminservice.Admin.Repositories.RealEstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/real-estate")
public class RealEstateController {
    @Autowired
    private RealEstateRepository realEstateRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    RealEstate addNewRealEstate (@RequestBody RealEstate realEstate) {
        return realEstateRepository.save(realEstate);
    }

    @GetMapping(path="/all/real-estates")
    public @ResponseBody Iterable<RealEstate> getAllUsers() {
        return realEstateRepository.findAll();
    }
}