package com.example.spring.handson.service;

import com.example.spring.handson.dto.UserDto;
import com.example.spring.handson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service
public class StartUpDataAddition implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.addUser(new UserDto("pratham_admin", passwordEncoder.encode("123"), "pratham_admin@kickdrum.com", "ROLE_ADMIN"));
        userRepository.addUser(new UserDto("pratham_user", passwordEncoder.encode("123"), "pratham_user@kickdrum.com", "ROLE_USER"));
    }
}
