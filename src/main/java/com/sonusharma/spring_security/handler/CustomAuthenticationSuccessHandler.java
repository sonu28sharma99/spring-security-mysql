package com.sonusharma.spring_security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Log the successful authentication
        log.info("User {} logged in successfully.", authentication.getName());

        // Retrieve user authorities/roles
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Log user roles for debugging purposes
        log.info("User {} has roles: {}", authentication.getName(), authorities);

        // Redirect based on user roles
        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            // Log admin redirect
            log.info("User {} is an admin. Redirecting to /admin/home", authentication.getName());
            response.sendRedirect("/admin/home");
        } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
            // Log user redirect
            log.info("User {} is a regular user. Redirecting to /user/home", authentication.getName());
            response.sendRedirect("/user/home");
        } else {
            // Log default redirect
            log.info("User {} has no specific role, redirecting to /health", authentication.getName());
            response.sendRedirect("/health");
        }
    }
}
