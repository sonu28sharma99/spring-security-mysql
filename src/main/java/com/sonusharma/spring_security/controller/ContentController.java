package com.sonusharma.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("health")
    public String healthCheck(){
        return "health";
    }


}
