package com.sonusharma.spring_security.service;

import com.sonusharma.spring_security.model.MyUser;
import com.sonusharma.spring_security.repository.MyUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Attempting to load user by username: {}", username);

        Optional<MyUser> user = myUserRepository.findByUsername(username);
        if(user.isEmpty()) {
            log.error("User not found with username: {}", username);
            throw new UsernameNotFoundException("USER NOT FOUND! \n" + username);
        }

        var userObj = user.get();
        log.info("User found: {}", userObj.getUsername());

        return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(getRoles(userObj.getRole()))
                .build();
    }

    private String[] getRoles(String role) {
        log.info("Assigning roles based on user role: {}", role);
        if(role.matches("ADMIN")) {
            log.info("User role is ADMIN, assigning both ADMIN and USER roles.");
            return new String[]{"ADMIN", "USER"};
        }
        log.info("User role is USER, assigning USER role.");
        return new String[]{"USER"};
    }
}
