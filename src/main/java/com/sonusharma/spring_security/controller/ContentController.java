package com.sonusharma.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContentController {

    @GetMapping("/home")
    public String handleWelcome(){
        return "home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome(){
        return "admin_home";
    }

    @GetMapping("/user/home")
    public String handleUserHome(){
        return "user_home";
    }


    @GetMapping("/health")
    public String healthCheck(){
        return "health";
    }

    @GetMapping("/login")
    public String handleLogin(){
        return "custom_login";
    }

}
