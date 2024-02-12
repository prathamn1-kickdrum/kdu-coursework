package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class to initialize data during application startup.
 */
@Service
@RequiredArgsConstructor
public class StartupDataService implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Runs the data initialization process during application startup.
     *
     * @param args The command-line arguments.
     */
    @Override
    public void run(String... args) {
        var superUser = userRepository.findByRole(Role.SUPERUSER);
        if (superUser.isEmpty()) {
            User user = User.builder()
                    .firstName("Pratham")
                    .lastName("Nagaria")
                    .email("prathamnagaria@gmail.com")
                    .username("prathamn1")
                    .name("Pratham Nagaria")
                    .password(passwordEncoder.encode("slgjsejgoespjsk"))
                    .role(Role.SUPERUSER)
                    .build();
            userRepository.save(user);
        }
    }
}
