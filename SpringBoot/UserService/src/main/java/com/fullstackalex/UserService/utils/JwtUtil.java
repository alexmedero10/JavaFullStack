package com.fullstackalex.UserService.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int expirationTime = 3600000;

    public static String generateToken(UserDetails userDetails) {
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryTime)
                .signWith(secretKey)
                .compact();
    }

    public static boolean validateToken(String token) {
        return false;
    }
}
