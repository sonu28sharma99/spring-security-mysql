package com.sonusharma.spring_security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class ContentController {

    @GetMapping("/home")
    public String handleWelcome() {
        log.info("Accessed the home page.");
        return "home";
    }

    @GetMapping("/admin/home")
    public String handleAdminHome() {
        log.info("Accessed the admin home page.");
        return "admin_home";
    }

    @GetMapping("/user/home")
    public String handleUserHome() {
        log.info("Accessed the user home page.");
        return "user_home";
    }

    @GetMapping("/health")
    public String healthCheck() {
        log.info("Accessed health check endpoint.");
        return "health";
    }

    @GetMapping("/login")
    public String handleLogin() {
        log.info("Accessed the login page.");
        return "custom_login";
    }

    @GetMapping("/register")
    public String registrationHandler() {
        log.info("Accessed the registration page.");
        return "register";
    }
}
