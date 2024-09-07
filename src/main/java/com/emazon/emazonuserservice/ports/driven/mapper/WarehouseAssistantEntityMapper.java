package com.emazon.emazonuserservice.ports.driven.mapper;


import com.emazon.emazonuserservice.domain.model.WarehouseAssistant;
import com.emazon.emazonuserservice.ports.driven.entity.WarehouseAssistantEntity;

public final class WarehouseAssistantEntityMapper {
    private WarehouseAssistantEntityMapper(){
        throw new IllegalStateException();
    }
    public static WarehouseAssistantEntity toEntity(WarehouseAssistant warehouseAssistant) {

        return   WarehouseAssistantEntity.builder()
                .name(warehouseAssistant.getName())
                .lastName(warehouseAssistant.getLastName())
                .identityDocument(warehouseAssistant.getIdentityDocument())
                .phoneNumber(warehouseAssistant.getPhoneNumber())
                .birthDate(warehouseAssistant.getBirthDate())
                .email(warehouseAssistant.getEmail())
                .password(warehouseAssistant.getPassword())
                .build();
    }

}