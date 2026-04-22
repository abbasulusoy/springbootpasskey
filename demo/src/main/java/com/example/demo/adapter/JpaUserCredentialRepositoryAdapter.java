package com.example.demo.adapter;

import com.example.demo.entity.PasskeyCredentialEntity;
import com.example.demo.repository.JpaUserCredentialRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.security.web.webauthn.api.Bytes;
import org.springframework.security.web.webauthn.api.CredentialRecord;
import org.springframework.security.web.webauthn.api.ImmutableCredentialRecord;
import org.springframework.security.web.webauthn.api.PublicKeyCose;
import org.springframework.security.web.webauthn.management.UserCredentialRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JpaUserCredentialRepositoryAdapter
        implements UserCredentialRepository {

    private final JpaUserCredentialRepository repository;

    public JpaUserCredentialRepositoryAdapter(JpaUserCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    public void delete(Bytes credentialId) {
        repository.deleteById(credentialId.toBase64UrlString());
    }

    @Override
    public void save(CredentialRecord credential) {

        PasskeyCredentialEntity entity = new PasskeyCredentialEntity();

        entity.setCredentialId(credential.getCredentialId().toString());
        entity.setUserEntityId(credential.getUserEntityUserId().toBase64UrlString());
        entity.setPublicKey(credential.getPublicKey().getBytes());
        entity.setSignatureCount(credential.getSignatureCount());

        repository.save(entity);
    }

    @Override
    public @Nullable CredentialRecord findByCredentialId(Bytes credentialId) {

        return repository.findById(credentialId.toBase64UrlString())
                .map(this::mapToRecord)
                .orElse(null);
    }

    @Override
    public List<CredentialRecord> findByUserId(Bytes userId) {
        return repository.findByUserEntityId(userId.toString())
                .stream()
                .map(this::mapToRecord)
                .toList();
    }



    private CredentialRecord mapToRecord(PasskeyCredentialEntity entity) {
        PublicKeyCose publicKeyCose = new PublicKeyCose() {
            @Override
            public byte[] getBytes() {
                return entity.getPublicKey();
            }
        };
        return ImmutableCredentialRecord.builder()
                .credentialId(Bytes.fromBase64(entity.getCredentialId()))
                .userEntityUserId(Bytes.fromBase64(entity.getUserEntityId()))
                .publicKey(publicKeyCose)
                .signatureCount(entity.getSignatureCount())
                .build();
    }

}