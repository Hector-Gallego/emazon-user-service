package com.emazon.emazonuserservice.domain.spi;

import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.model.WarehouseAssistant;
import com.emazon.emazonuserservice.ports.driven.entity.RoleEntity;
import com.emazon.emazonuserservice.ports.driven.entity.WarehouseAssistantEntity;

import java.util.Optional;

public interface IWarehouseAssistantPersistencePort {

    void saveWarehouseAssistant(WarehouseAssistant warehouseAssistant);
    Boolean existByIdentityDocument(String identityDocument);
    Boolean rolNameExist(String role);



}