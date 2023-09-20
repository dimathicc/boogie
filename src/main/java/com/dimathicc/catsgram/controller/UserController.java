package com.dimathicc.catsgram.controller;

import com.dimathicc.catsgram.model.User;
import com.dimathicc.catsgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{login}")
    public Optional<User> getUser(@PathVariable String login){
        return userService.findUserById(login);
    }
}
