package com.example.demo.service;

import com.example.demo.entity.PasskeyUserEntity;
import com.example.demo.repository.JpaPublicKeyCredentialUserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasskeyUserService {

    private final JpaPublicKeyCredentialUserEntityRepository repository;

    public PasskeyUserService(JpaPublicKeyCredentialUserEntityRepository repository) {
        this.repository = repository;
    }

    public Optional<PasskeyUserEntity> findByUsername(String username) {
        return repository.findById(username);
    }

    public PasskeyUserEntity createUserIfNotExists(String username) {

        return repository.findById(username)
                .orElseGet(() -> {
                    PasskeyUserEntity user = new PasskeyUserEntity();
                    user.setId(username);
                    user.setName(username);
                    user.setDisplayName(username);

                    return repository.save(user);
                });
    }
}