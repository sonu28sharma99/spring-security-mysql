package com.sonusharma.spring_security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Log the successful authentication
        System.out.println("User " + authentication.getName() + " logged in successfully.");

        // Redirect based on user roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            // Redirect to admin page
            response.sendRedirect("/admin/home");
        } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            // Redirect to user home page
            response.sendRedirect("/user/home");
        } else {
            // Default redirect if no specific role matches
            response.sendRedirect("/health");
        }
    }
}
