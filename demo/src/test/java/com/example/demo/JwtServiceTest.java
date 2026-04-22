package com.example.demo;

import com.example.demo.service.JwtService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class JwtServiceTest {

    @Test
    void shouldGenerateToken() {

        JwtService jwtService = new JwtService("ZafeSuperSecretKeyForJwtSigning1234567890!!");

        String token = jwtService.createToken("user123", List.of("USER"));

        assertNotNull(token);
        assertTrue(token.startsWith("ey")); // JWT prefix
    }
}