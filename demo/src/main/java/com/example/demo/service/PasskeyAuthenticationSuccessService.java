package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasskeyAuthenticationSuccessService {

    private final JwtService jwtService;

    public PasskeyAuthenticationSuccessService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String issueToken(Authentication authentication) {
        return jwtService.createToken(
                authentication.getName(),
                List.of("GROUP_MEMBER")
        );
    }
}