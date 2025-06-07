package com.logico.emailSender.interfaces.controller;


import com.logico.emailSender.infra.security.JwtService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenController {

    private final JwtService jwtService;

    @Value("${jwt.generation.secret}")
    private String generationSecret;



    @GetMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestHeader("X-SECRET-KEY") String headerSecret) {
        if (!generationSecret.equals(headerSecret)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acesso não autorizado");
        }

        String token = jwtService.generateToken();
        return ResponseEntity.ok(token);
    }
}