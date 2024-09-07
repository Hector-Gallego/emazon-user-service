package com.emazon.emazonuserservice.ports.driving.controller;

import com.emazon.emazonuserservice.domain.api.IWarehouseAssistantServicePort;
import com.emazon.emazonuserservice.ports.driving.dto.WareHouseAssistantRequestDto;
import com.emazon.emazonuserservice.ports.driving.mapper.WarehouseAssistantRequestMapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final IWarehouseAssistantServicePort warehouseAssistantServicePort;

    public UserController(IWarehouseAssistantServicePort warehouseAssistantServicePort) {
        this.warehouseAssistantServicePort = warehouseAssistantServicePort;
    }

    @PostMapping
    ResponseEntity<Void> saveWarehouseAssistant(@RequestBody WareHouseAssistantRequestDto wareHouseAssistantRequestDto){
        warehouseAssistantServicePort.saveWareHouseAssistant(WarehouseAssistantRequestMapperImpl.toDomain(wareHouseAssistantRequestDto));
        return ResponseEntity.ok().build();
    }


}