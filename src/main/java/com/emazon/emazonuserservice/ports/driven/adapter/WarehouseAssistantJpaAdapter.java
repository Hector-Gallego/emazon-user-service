package com.emazon.emazonuserservice.ports.driven.adapter;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.model.WarehouseAssistant;
import com.emazon.emazonuserservice.domain.spi.IWarehouseAssistantPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleConstants;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;
import com.emazon.emazonuserservice.ports.driven.entity.RoleEntity;
import com.emazon.emazonuserservice.ports.driven.entity.WarehouseAssistantEntity;
import com.emazon.emazonuserservice.ports.driven.mapper.WarehouseAssistantEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.IRoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.IWarehouseAssistantRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WarehouseAssistantJpaAdapter implements IWarehouseAssistantPersistencePort {


    private final IWarehouseAssistantRepository warehouseAssistantRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public WarehouseAssistantJpaAdapter(IWarehouseAssistantRepository warehouseAssistantRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.warehouseAssistantRepository = warehouseAssistantRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveWarehouseAssistant(WarehouseAssistant warehouseAssistant) {

        RoleEntity roleEntity = roleRepository
                .findByName(RoleConstants.WAREHOUSE_ASSISTANT.name())
                .orElseThrow(() -> new RoleNotFoundException(
                        String.format(ValidationErrorConstants.ROLE_NOT_FOUND.getMessage(),
                                RoleConstants.WAREHOUSE_ASSISTANT)
                ));

        WarehouseAssistantEntity assistantEntity = WarehouseAssistantEntityMapper.toEntity(warehouseAssistant);

        assistantEntity.setPassword(passwordEncoder.encode(assistantEntity.getPassword()));
        assistantEntity.setRole(roleEntity);
        warehouseAssistantRepository.save(assistantEntity);
    }

    @Override
    public Boolean existByIdentityDocument(String identityDocument) {
        return warehouseAssistantRepository.findByIdentityDocument(identityDocument).isPresent();
    }

    @Override
    public Boolean rolNameExist(String role) {
        return roleRepository.findByName(role).isPresent();
    }


}
