package com.kdu.smarthome.service.impl;

import com.kdu.smarthome.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Implementation of the JwtService interface providing methods for JWT token management.
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value(value="${spring.jwt.secret}")
    private String secret;

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Generates a JWT token for the provided UserDetails.
     *
     * @param userDetails The UserDetails object containing user information.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignKey(), Jwts.SIG.HS256)
                .compact();
    }

    /**
     * Generates a refresh token for the provided UserDetails and extra claims.
     *
     * @param extraClaims  Extra claims to include in the token.
     * @param userDetails  The UserDetails object containing user information.
     * @return The generated refresh token.
     */
    @Override
    public String generateRefreshToken(HashMap<String, ?> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(extraClaims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 7))
                .signWith(getSignKey())
                .compact();
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param token The JWT token.
     * @return The extracted username.
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Checks if the provided JWT token is valid for the provided UserDetails.
     *
     * @param token       The JWT token.
     * @param userDetails The UserDetails object containing user information.
     * @return True if the token is valid, otherwise false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Checks if the provided JWT token is expired.
     *
     * @param token The JWT token.
     * @return True if the token is expired, otherwise false.
     */
    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

}
