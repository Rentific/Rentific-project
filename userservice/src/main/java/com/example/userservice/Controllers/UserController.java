package com.example.userservice.Controllers;
import com.example.userservice.ErrorHandling.InvalidRequestException;
import com.example.userservice.ErrorHandling.UserNotFoundException;
import com.example.userservice.Service.UserService;
import com.example.userservice.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService _userService;

    public UserController(UserService userService) {
        this._userService = userService;
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> findAllUsers() {
        return _userService.findAllUsers();
    }

    @GetMapping("/{id}")
    ResponseEntity<User> findUserById(@PathVariable(value = "id") Integer id)  throws InvalidRequestException, UserNotFoundException {
        return this._userService.findUserById(id);
    }

    @PostMapping("/add")
    ResponseEntity<User> addNewUser(@RequestBody User user) throws InvalidRequestException, UserNotFoundException   {
        return _userService.saveUser(user);
    }
    @PutMapping("/update/{id}")
    ResponseEntity<User> updateUser(@PathVariable(value = "id") Integer id, @RequestBody User user) throws InvalidRequestException, UserNotFoundException  {
        return this._userService.updateExistingUser(id, user);
    }
    @DeleteMapping("delete/{id}")
    ResponseEntity deleteUser(@PathVariable(value = "id") Integer id) throws InvalidRequestException, UserNotFoundException   {
        return this._userService.deleteUser(id);
    }
 @GetMapping(value = "", params = "email")
    public ResponseEntity<User> UserByMail(@RequestParam("email") String Email)  throws UserNotFoundException {
        return this._userService.findUserByEmail(Email);
    }
}



