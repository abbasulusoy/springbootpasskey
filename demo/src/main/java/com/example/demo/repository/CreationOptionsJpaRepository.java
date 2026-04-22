package com.example.demo.repository;

import com.example.demo.entity.CreationOptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreationOptionsJpaRepository
        extends JpaRepository<CreationOptionsEntity, String> {
}