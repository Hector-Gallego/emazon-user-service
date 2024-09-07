package com.emazon.emazonuserservice.ports.driving.mapper;

import com.emazon.emazonuserservice.domain.model.WarehouseAssistant;
import com.emazon.emazonuserservice.ports.driving.dto.WareHouseAssistantRequestDto;
import lombok.experimental.UtilityClass;
@UtilityClass
public final class WarehouseAssistantRequestMapperImpl {

    public static WarehouseAssistant toDomain(WareHouseAssistantRequestDto wareHouseAssistantRequestDto) {
        if(wareHouseAssistantRequestDto == null){
            return null;
        }

        return (WarehouseAssistant) new WarehouseAssistant.Builder()
                .name(wareHouseAssistantRequestDto.getName())
                .lastName(wareHouseAssistantRequestDto.getLastName())
                .identityDocument(wareHouseAssistantRequestDto.getIdentityDocument())
                .phoneNumber(wareHouseAssistantRequestDto.getPhoneNumber())
                .birthDate(wareHouseAssistantRequestDto.getBirthDate())
                .email(wareHouseAssistantRequestDto.getEmail())
                .password(wareHouseAssistantRequestDto.getPassword())
                .build();
    }

}