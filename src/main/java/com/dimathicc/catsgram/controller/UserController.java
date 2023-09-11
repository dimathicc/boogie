package com.dimathicc.catsgram.controller;

import com.dimathicc.catsgram.model.User;
import com.dimathicc.catsgram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping("/users/")
    public User update(@RequestBody User updatedUser) {
        return userService.update(updatedUser);
    }

}
