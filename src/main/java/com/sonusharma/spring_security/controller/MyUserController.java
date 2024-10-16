package com.sonusharma.spring_security.controller;

import com.sonusharma.spring_security.model.MyUser;
import com.sonusharma.spring_security.service.MyUserDetailService;
import com.sonusharma.spring_security.service.UserService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app")
public class MyUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user){
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public List<MyUser> getAllUsers(){
        return userService.getAllUsers();
    }



}
