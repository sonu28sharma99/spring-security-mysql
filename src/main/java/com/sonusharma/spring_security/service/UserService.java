package com.sonusharma.spring_security.service;

import com.sonusharma.spring_security.model.MyUser;
import com.sonusharma.spring_security.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserRepository myUserRepository;

    public MyUser createUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return myUserRepository.save(user);
    }

    public List<MyUser> getAllUsers() {
        return myUserRepository.findAll();
    }
}
