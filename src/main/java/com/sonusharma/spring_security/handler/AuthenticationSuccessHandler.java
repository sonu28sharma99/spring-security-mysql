package com.sonusharma.spring_security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

        // Log successful authentication
        log.info("Authentication successful for user: {}", authentication.getName());

        // Check if the authenticated user has the ROLE_ADMIN authority
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority ->
                        grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        // Log role check
        if (isAdmin) {
            log.info("User {} has ROLE_ADMIN, redirecting to /admin/home", authentication.getName());
            // Redirect admins to the admin home page
            setDefaultTargetUrl("/admin/home");
        } else {
            log.info("User {} does not have ROLE_ADMIN, redirecting to /user/home", authentication.getName());
            // Redirect regular users to the user home page
            setDefaultTargetUrl("/user/home");
        }

        // Call the parent method to complete the process
        super.onAuthenticationSuccess(request, response, authentication);

        // Log that redirection is completed
        log.info("Redirection completed for user: {}", authentication.getName());
    }

}
