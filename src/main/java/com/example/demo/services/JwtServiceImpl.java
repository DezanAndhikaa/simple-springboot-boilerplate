package com.example.demo.services;

import com.example.demo.interfaces.IJwtService;
import com.example.demo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements IJwtService {
    @Override
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getJwtKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expirationTime = now.plusSeconds(24 * 60 * 60);

        return Jwts.builder().
                setSubject(user.getUsername()).
                claim("role", user.getRole()).
                claim("id", user.getId()).
                signWith(SignatureAlgorithm.HS256, getJwtKey()).
                setExpiration(Date.from(expirationTime)).
                compact();
    }

    @Override
    public Key getJwtKey() {
        byte[] keyBytes = Decoders.BASE64.decode("b108678f3be233fcd93c462f7b8128e01fde199aasdasdasdasdasdsadasdasdasd");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
