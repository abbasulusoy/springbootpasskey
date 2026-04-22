package com.example.demo.repository;

import com.example.demo.entity.PasskeyCredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaUserCredentialRepository
        extends JpaRepository<PasskeyCredentialEntity, String> {

    List<PasskeyCredentialEntity> findByUserEntityId(String userId);
}