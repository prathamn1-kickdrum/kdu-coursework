package com.kdu.smarthome.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JwtService {
    String extractUserName(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);

    boolean isTokenExpired(String token);

    String generateRefreshToken(HashMap<String, ?> extraClaims, UserDetails userDetails);
}
