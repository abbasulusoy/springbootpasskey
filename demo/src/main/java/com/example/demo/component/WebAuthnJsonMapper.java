package com.example.demo.component;

import org.springframework.security.web.webauthn.api.PublicKeyCredentialCreationOptions;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class WebAuthnJsonMapper {

    private final ObjectMapper objectMapper;

    public WebAuthnJsonMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public String toJson(PublicKeyCredentialCreationOptions options) {
        try {
            return objectMapper.writeValueAsString(options);
        } catch (Exception e) {
            throw new RuntimeException("Serialization failed", e);
        }
    }

    public PublicKeyCredentialCreationOptions fromJson(String json) {
        try {
            return objectMapper.readValue(
                json,
                PublicKeyCredentialCreationOptions.class
            );
        } catch (Exception e) {
            throw new RuntimeException("Deserialization failed", e);
        }
    }
}