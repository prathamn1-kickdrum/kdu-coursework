package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.response.RegisterResponseDto;
import com.kdu.smarthome.dto.request.UserLoginRequest;
import com.kdu.smarthome.dto.request.UserRefreshRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.custom.BadLoginCredentialsException;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.service.AuthenticationService;
import com.kdu.smarthome.service.JwtService;
import com.kdu.smarthome.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterResponseDto registerAndLoginUser(UserRegisterRequest myDto) {
        User user = User.builder()
                        .firstName(myDto.getFirstName())
                        .lastName(myDto.getLastName())
                        .email(myDto.getEmailId())
                        .name(myDto.getName())
                        .username(myDto.getUsername())
                        .password(passwordEncoder.encode(myDto.getPassword()))
                        .role(Role.USER)
                        .build();
        User registeredUser = userRepository.save(user);


        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(myDto.getUsername())
                .password(myDto.getPassword())
                .build();

        RegisterResponseDto registerResponseDto = loginUser(userLoginRequest);
        registerResponseDto.setMsg("User registered & logged in successfully: "+registeredUser.getUserId());
        return registerResponseDto;
    }

    public RegisterResponseDto loginUser(UserLoginRequest myDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(myDto.getUsername(),myDto.getPassword()));
        var user = userRepository.findByUsername(myDto.getUsername())
                    .orElseThrow(()->new BadLoginCredentialsException("Invalid email or password"));

        var jwtToken = jwtService.generateToken(user);

        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        return RegisterResponseDto.builder().token(jwtToken).refreshToken(refreshToken).build();
    }

    public RegisterResponseDto refreshUser(UserRefreshRequest myDto) {
        String username = jwtService.extractUserName(myDto.getRefreshToken());
        User user = userRepository.findByUsername(username).orElseThrow();
        if(jwtService.isTokenValid(myDto.getRefreshToken(),user)) {
            var jwtToken = jwtService.generateToken(user);
            return RegisterResponseDto.builder().token(jwtToken).refreshToken(myDto.getRefreshToken()).build();
        }
        return null;
    }
}
