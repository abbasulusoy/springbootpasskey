package com.example.demo;

import com.example.demo.controller.AuthTokenController;
import com.example.demo.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthTokenControllerTest {

    @Test
    void shouldReturnToken() {

        JwtService jwtService = new JwtService("ZafeSuperSecretKeyForJwtSigning1234567890!!");
        AuthTokenController controller = new AuthTokenController(jwtService);

        var auth = new UsernamePasswordAuthenticationToken("abul", null, List.of());

        Map<String, String> result = controller.token(auth);

        assertNotNull(result.get("token"));
    }
}