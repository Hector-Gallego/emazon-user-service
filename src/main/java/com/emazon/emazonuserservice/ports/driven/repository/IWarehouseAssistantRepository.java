package com.emazon.emazonuserservice.ports.driven.repository;

import com.emazon.emazonuserservice.ports.driven.entity.WarehouseAssistantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IWarehouseAssistantRepository extends JpaRepository<WarehouseAssistantEntity, Long> {
    Optional<WarehouseAssistantEntity> findByIdentityDocument(String identityDocument);
}

