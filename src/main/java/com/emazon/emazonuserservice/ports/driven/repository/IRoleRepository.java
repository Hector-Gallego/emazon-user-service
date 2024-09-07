package com.emazon.emazonuserservice.ports.driven.repository;

import com.emazon.emazonuserservice.domain.model.Role;
import com.emazon.emazonuserservice.ports.driven.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}
