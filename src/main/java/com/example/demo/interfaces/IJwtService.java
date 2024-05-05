package com.example.demo.interfaces;

import com.example.demo.model.User;
import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public interface IJwtService {
    Claims extractAllClaims(String token);
    String generateToken(User user);
    Key getJwtKey();
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    Date extractExpiration(String token);
    boolean isTokenExpired(String token);
}
