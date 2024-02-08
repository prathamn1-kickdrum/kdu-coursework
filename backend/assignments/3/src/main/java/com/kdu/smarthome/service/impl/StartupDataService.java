package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.util.Role;


@Service
@RequiredArgsConstructor
public class StartupDataService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User superUser = userRepository.findByRole(Role.SUPERUSER);
        if(null==superUser) {
            User user = User.builder()
                    .firstName("Pratham")
                    .lastName("Nagaria")
                    .email("prathamnagaria@gmail.com")
                    .password(passwordEncoder.encode("password"))
                    .role(Role.SUPERUSER)
                    .build();
            userRepository.save(user);
        }
    }
}
