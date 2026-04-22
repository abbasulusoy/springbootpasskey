package com.example.demo.repository;

import com.example.demo.entity.PasskeyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPublicKeyCredentialUserEntityRepository
        extends JpaRepository<PasskeyUserEntity, String> {
}