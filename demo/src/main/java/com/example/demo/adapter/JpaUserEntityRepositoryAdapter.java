package com.example.demo.adapter;

import com.example.demo.entity.PasskeyUserEntity;
import com.example.demo.repository.JpaPublicKeyCredentialUserEntityRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.ImmutablePublicKeyCredentialUserEntity;
import org.springframework.security.web.webauthn.management.PublicKeyCredentialUserEntityRepository;
import org.springframework.security.web.webauthn.api.PublicKeyCredentialUserEntity;
import org.springframework.stereotype.Component;

@Component
public class JpaUserEntityRepositoryAdapter
        implements PublicKeyCredentialUserEntityRepository {

    private final JpaPublicKeyCredentialUserEntityRepository repository;

    public JpaUserEntityRepositoryAdapter(
            JpaPublicKeyCredentialUserEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public @Nullable PublicKeyCredentialUserEntity findById(Bytes id) {
        String userId = id.toBase64UrlString();

        PasskeyUserEntity entity = repository.findById(userId)
                .orElseGet(() -> {
                    PasskeyUserEntity newUser = new PasskeyUserEntity();
                    newUser.setId(userId);

                    // ✔ fallback: use same value
                    newUser.setName(userId);
                    newUser.setDisplayName(userId);

                    return repository.save(newUser);
                });

        return mapToModel(entity);
    }

    @Override
    public PublicKeyCredentialUserEntity findByUsername(String username) {

        //  IMPORTANT: create user if not exists
        PasskeyUserEntity entity = repository.findById(username)
                .orElseGet(() -> {
                    PasskeyUserEntity newUser = new PasskeyUserEntity();
                    newUser.setId(username);
                    newUser.setName(username);
                    newUser.setDisplayName(username);

                    return repository.save(newUser);
                });

        return mapToModel(entity);
    }

    @Override
    public void save(PublicKeyCredentialUserEntity user) {

        PasskeyUserEntity entity = new PasskeyUserEntity();
        entity.setId(user.getId().toBase64UrlString());
        entity.setName(user.getName());
        entity.setDisplayName(user.getDisplayName());

        repository.save(entity);
    }

    @Override
    public void delete(Bytes id) {

    }

    private PublicKeyCredentialUserEntity mapToModel(PasskeyUserEntity entity) {

        return ImmutablePublicKeyCredentialUserEntity.builder().
                id(Bytes.fromBase64(entity.getId()))
                .name(entity.getName())
                .displayName(entity.getDisplayName()).build();
    }
}