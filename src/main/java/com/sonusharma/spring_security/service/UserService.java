package com.sonusharma.spring_security.service;

import com.sonusharma.spring_security.model.MyUser;
import com.sonusharma.spring_security.repository.MyUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserRepository myUserRepository;

    public MyUser createUser(MyUser user) {
        log.info("Creating a new user with username: {}", user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        MyUser savedUser = myUserRepository.save(user);

        log.info("User created successfully with username: {}", savedUser.getUsername());
        return savedUser;
    }

    public List<MyUser> getAllUsers() {
        log.info("Fetching all users from the database.");
        List<MyUser> users = myUserRepository.findAll();
        log.info("Total users fetched: {}", users.size());
        return users;
    }
}
