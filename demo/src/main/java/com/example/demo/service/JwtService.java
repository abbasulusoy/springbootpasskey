package com.example.demo.service;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${app.jwt.secret}") String secret) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    public String createToken(String subject, Collection<String> authorities) {
        Instant now = Instant.now();
        Instant exp = now.plus(Duration.ofMinutes(60));

        return Jwts.builder()
                .subject(subject)
                .issuer("zafe")
                .audience().add("zafe-api").and()
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claim("scope", authorities)
                .signWith(secretKey)
                .compact();
    }
}