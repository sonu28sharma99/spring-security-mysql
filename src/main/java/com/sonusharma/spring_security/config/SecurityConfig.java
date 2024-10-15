package com.sonusharma.spring_security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/health", "/home").permitAll();
                    registry.requestMatchers("/admin/**").hasRole("admin");
                    registry.requestMatchers("/user/**").hasRole("user");
                    registry.anyRequest().authenticated();
                }).build();
    }
}
