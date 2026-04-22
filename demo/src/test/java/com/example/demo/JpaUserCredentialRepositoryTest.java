package com.example.demo;

import com.example.demo.entity.PasskeyCredentialEntity;
import com.example.demo.repository.JpaUserCredentialRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
//@DataJpaTest TODO
class JpaUserCredentialRepositoryTest {

    @Autowired
    private JpaUserCredentialRepository repository;

    @Test
    void shouldSaveCredential() {

        PasskeyCredentialEntity entity = new PasskeyCredentialEntity();
        entity.setCredentialId("cred123");
        entity.setUserEntityId("user1");
        entity.setPublicKey(new byte[]{1,2,3});
        entity.setSignatureCount(0);

        repository.save(entity);

        var found = repository.findById("cred123");

        assertTrue(found.isPresent());
    }
}