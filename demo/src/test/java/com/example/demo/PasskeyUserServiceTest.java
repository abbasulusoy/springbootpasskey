package com.example.demo;

import com.example.demo.repository.JpaPublicKeyCredentialUserEntityRepository;
import com.example.demo.service.PasskeyUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PasskeyUserServiceTest {

    @Test
    void shouldCreateUserIfNotExists() {

        var repo = Mockito.mock(JpaPublicKeyCredentialUserEntityRepository.class);

        Mockito.when(repo.findById("abul")).thenReturn(Optional.empty());

        PasskeyUserService service = new PasskeyUserService(repo);

        var user = service.createUserIfNotExists("abul");

        assertNotNull(user);
        assertEquals("abul", user.getId());
    }
}