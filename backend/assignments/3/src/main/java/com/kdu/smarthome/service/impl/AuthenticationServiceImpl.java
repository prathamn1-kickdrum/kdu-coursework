package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.dto.request.UserLoginRequest;
import com.kdu.smarthome.dto.request.UserRefreshRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.dto.response.RegisterResponseDto;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.service.AuthenticationService;
import com.kdu.smarthome.service.JwtService;
import com.kdu.smarthome.util.ExceptionType;
import com.kdu.smarthome.util.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

/**
 * Implementation of the AuthenticationService interface providing methods for user authentication and registration.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Registers a new user and logs them in.
     *
     * @param myDto The UserRegisterRequest containing user registration details.
     * @return A RegisterResponseDto with authentication tokens.
     * @throws CustomException If an error occurs during user registration or login.
     */
    public RegisterResponseDto registerAndLoginUser(UserRegisterRequest myDto) throws CustomException {
        // Register user
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

        // Login registered user
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(myDto.getUsername())
                .password(myDto.getPassword())
                .build();
        RegisterResponseDto registerResponseDto = loginUser(userLoginRequest);
        registerResponseDto.setMsg("User registered & logged in successfully: " + registeredUser.getUserId());
        return registerResponseDto;
    }

    /**
     * Logs in a user.
     *
     * @param myDto The UserLoginRequest containing user login details.
     * @return A RegisterResponseDto with authentication tokens.
     * @throws CustomException If the user is not found or login fails.
     */
    public RegisterResponseDto loginUser(UserLoginRequest myDto) throws CustomException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(myDto.getUsername(), myDto.getPassword()));
        Optional<User> user = userRepository.findByUsername(myDto.getUsername());
        if (user.isPresent()) {
            var jwtToken = jwtService.generateToken(user.get());
            var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user.get());
            return RegisterResponseDto.builder().token(jwtToken).refreshToken(refreshToken).build();
        } else {
            throw new CustomException("User not found", ExceptionType.NoUserFoundException, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Refreshes a user's authentication token.
     *
     * @param myDto The UserRefreshRequest containing the refresh token.
     * @return A RegisterResponseDto with a new authentication token.
     * @throws CustomException If the user is not found or token refresh fails.
     */
    public RegisterResponseDto refreshUser(UserRefreshRequest myDto) throws CustomException {
        String username = jwtService.extractUserName(myDto.getRefreshToken());
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException("User not found", ExceptionType.NoUserFoundException, HttpStatus.NOT_FOUND));
        if (jwtService.isTokenValid(myDto.getRefreshToken(), user)) {
            var jwtToken = jwtService.generateToken(user);
            return RegisterResponseDto.builder().token(jwtToken).refreshToken(myDto.getRefreshToken()).build();
        }
        return null;
    }
}
