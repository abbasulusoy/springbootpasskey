package com.example.demo.component;

import com.example.demo.entity.CreationOptionsEntity;
import com.example.demo.repository.CreationOptionsJpaRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialCreationOptions;
import org.springframework.security.web.webauthn.registration.PublicKeyCredentialCreationOptionsRepository;
import org.springframework.stereotype.Component;


import java.time.Instant;

@Component
public class JpaCreationOptionsRepository
        implements PublicKeyCredentialCreationOptionsRepository {

    private final CreationOptionsJpaRepository repository;
    private final WebAuthnJsonMapper mapper;

    public JpaCreationOptionsRepository(
            CreationOptionsJpaRepository repository,
            WebAuthnJsonMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }



    @Override
    public void save(HttpServletRequest request, HttpServletResponse response, org.springframework.security.web.webauthn.api.@Nullable PublicKeyCredentialCreationOptions options) {
        String sessionId = request.getSession().getId();

        CreationOptionsEntity entity = new CreationOptionsEntity();
        entity.setSessionId(sessionId);
        entity.setOptionsJson(mapper.toJson(options));
        entity.setCreatedAt(Instant.now());

        repository.save(entity);
    }

    @Override
    public PublicKeyCredentialCreationOptions load(HttpServletRequest request) {
        String sessionId = request.getSession().getId();

        return repository.findById(sessionId)
                .map(e -> mapper.fromJson(e.getOptionsJson()))
                .orElse(null);
    }


}