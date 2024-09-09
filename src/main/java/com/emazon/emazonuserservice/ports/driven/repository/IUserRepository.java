package com.emazon.emazonuserservice.ports.driven.repository;

import com.emazon.emazonuserservice.ports.driven.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdentityDocument(String identityDocument);
}

