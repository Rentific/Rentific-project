package com.example.adminservice.adminservice.Admin.Controllers;

import com.example.adminservice.adminservice.Admin.Models.StaffUser;
import com.example.adminservice.adminservice.Admin.Models.State;
import com.example.adminservice.adminservice.Admin.Repositories.StaffUserRepository;
import com.example.adminservice.adminservice.Admin.Repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/state")
public class StateController {
    @Autowired
    private StateRepository stateRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    State addNewState (@RequestBody State state) {
        return stateRepository.save(state);
    }

    @GetMapping(path="/all/states")
    public @ResponseBody Iterable<State> getAllStates() {
        return stateRepository.findAll();
    }
}