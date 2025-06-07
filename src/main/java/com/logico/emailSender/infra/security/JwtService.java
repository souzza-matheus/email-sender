package com.logico.emailSender.infra.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private Key signingKey;
    private JwtParser jwtParser;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        signingKey = Keys.hmacShaKeyFor(keyBytes);

        jwtParser = Jwts.parser()
                .setSigningKey(signingKey)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken() {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(60 * 60); // 1 hora

        return Jwts.builder()
                .setSubject("internal-service")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiration))
                .addClaims(Map.of("issuer", "email-backend"))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }
}