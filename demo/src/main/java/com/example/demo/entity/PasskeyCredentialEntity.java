package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Entity
@Table(name = "user_credential")
@Data
public class PasskeyCredentialEntity {
    @Id
    private String credentialId;

    private String userEntityId;

    @Lob
    private byte[] publicKey;

    private long signatureCount;

    private Boolean uvInitialized;
    private Boolean backupEligible;
    private Boolean backupState;
    private String transports;
}