package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.UserLoginRequest;
import com.kdu.smarthome.dto.request.UserRefreshRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.dto.response.RegisterResponseDto;
import com.kdu.smarthome.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerAndLoginUser(@RequestBody UserRegisterRequest myDto) {
        System.out.println(myDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.registerAndLoginUser(myDto));
    }

    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDto> loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(authenticationService.loginUser(userLoginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RegisterResponseDto> refreshUser(@RequestBody UserRefreshRequest myDto) {
        return ResponseEntity.ok(authenticationService.refreshUser(myDto));
    }
}
