package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "public_key_credential_user_entity")
@Data
public class PasskeyUserEntity {

    @Id
    private String id;

    private String name;

    private String displayName;
}