package com.emazon.emazonuserservice.ports.driven.repository;

import com.emazon.emazonuserservice.ports.driven.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdentityDocument(String identityDocument);
    Optional<UserEntity> findByEmail(String email);
}

