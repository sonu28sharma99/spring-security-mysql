package com.sonusharma.spring_security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {
        // Check if the authenticated user has the ROLE_ADMIN authority
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        // Redirect to different pages based on role
        if (isAdmin) {
            // Redirect admins to the admin home page
            setDefaultTargetUrl("/admin/home");
        } else {
            // Redirect regular users to the user home page
            setDefaultTargetUrl("/user/home");
        }

        // Call the parent method to complete the process
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
