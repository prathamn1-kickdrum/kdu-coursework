package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.UserLoginRequest;
import com.kdu.smarthome.dto.request.UserRefreshRequest;
import com.kdu.smarthome.dto.request.UserRegisterRequest;
import com.kdu.smarthome.dto.response.RegisterResponseDto;
import com.kdu.smarthome.exception.CustomException;
import com.kdu.smarthome.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for handling authentication endpoints.
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    /**
     * Registers and logs in a user.
     *
     * @param myDto UserRegisterRequest instance containing registration data.
     * @return ResponseEntity containing the response with registration details.
     * @throws CustomException If an error occurs during registration.
     */
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerAndLoginUser(@RequestBody @Valid UserRegisterRequest myDto) throws CustomException {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.registerAndLoginUser(myDto));
    }

    /**
     * Logs in a user.
     *
     * @param userLoginRequest UserLoginRequest instance containing login credentials.
     * @return ResponseEntity containing the response with login details.
     * @throws CustomException If an error occurs during login.
     */
    @PostMapping("/login")
    public ResponseEntity<RegisterResponseDto> loginUser(@RequestBody @Valid UserLoginRequest userLoginRequest) throws CustomException {
        return ResponseEntity.ok(authenticationService.loginUser(userLoginRequest));
    }

    /**
     * Refreshes user credentials.
     *
     * @param myDto UserRefreshRequest instance containing refresh token.
     * @return ResponseEntity containing the response with refreshed user details.
     * @throws CustomException If an error occurs during token refresh.
     */
    @PostMapping("/refresh")
    public ResponseEntity<RegisterResponseDto> refreshUser(@RequestBody @Valid  UserRefreshRequest myDto) throws CustomException {
        return ResponseEntity.ok(authenticationService.refreshUser(myDto));
    }
}
