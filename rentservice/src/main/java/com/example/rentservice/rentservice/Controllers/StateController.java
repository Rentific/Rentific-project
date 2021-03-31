package com.example.rentservice.rentservice.Controllers;

import com.example.rentservice.rentservice.Models.State;
import com.example.rentservice.rentservice.Repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController // This means that this class is a Controller
@RequestMapping(path="/state") // This means URL's start with /demo (after Application path)
public class StateController {
    private StateRepository stateRepository;

    @PostMapping(path="/add")
    public @ResponseBody State add (@RequestBody State state) {
        return stateRepository.save(state);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<State> getAllStates() {
        // This returns a JSON or XML with the users
        return stateRepository.findAll();
    }
}