package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "webauthn_creation_options")
@Data
public class CreationOptionsEntity {

    @Id
    private String sessionId;

    //TODO USE IN POSTGRESS @Column(columnDefinition = "jsonb")
    //private String optionsJson;

    @Column(columnDefinition = "text")
    private String optionsJson;

    private Instant createdAt;

    // getters / setters
}