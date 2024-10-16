package com.sonusharma.spring_security.controller;

import com.sonusharma.spring_security.model.MyUser;
import com.sonusharma.spring_security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
@Slf4j
public class MyUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user) {
        log.info("Received request to create user: {}", user.getUsername());
        MyUser createdUser = userService.createUser(user);
        log.info("User {} created successfully.", createdUser.getUsername());
        return createdUser;
    }

    @GetMapping("/users")
    public List<MyUser> getAllUsers() {
        log.info("Received request to fetch all users.");
        List<MyUser> users = userService.getAllUsers();
        log.info("Fetched {} users.", users.size());
        return users;
    }
}
