package com.sonusharma.spring_security.service;

import com.sonusharma.spring_security.model.MyUser;
import com.sonusharma.spring_security.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = myUserRepository.findByUsername(username);
        if(user.isEmpty())
            throw new UsernameNotFoundException("USER NOT FOUND! \n" + user);

        var userObj = user.get();
        return User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .roles(getRoles(userObj.getRole()))
                .build();
    }

    private String[] getRoles(String role) {
        if(role.matches("ADMIN")) return new String[]{"ADMIN", "USER"};
        return new String[]{"USER"};
    }
}
