package com.example.demo.controller;

import com.example.demo.service.JwtService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthTokenController {

    private final JwtService jwtService;

    public AuthTokenController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/token")
    public Map<String, String> token(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }
        return Map.of(
                "token",
                jwtService.createToken(authentication.getName(), List.of("USER"))
        );
    }
}