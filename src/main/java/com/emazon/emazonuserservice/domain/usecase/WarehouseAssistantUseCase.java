package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.api.IWarehouseAssistantServicePort;
import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.model.WarehouseAssistant;
import com.emazon.emazonuserservice.domain.spi.IWarehouseAssistantPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleConstants;
import com.emazon.emazonuserservice.domain.util.UserValidatorUtil;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;

public class WarehouseAssistantUseCase implements IWarehouseAssistantServicePort {

    private final IWarehouseAssistantPersistencePort warehouseAssistantPersistencePort;

    public WarehouseAssistantUseCase(IWarehouseAssistantPersistencePort warehouseAssistantPersistencePort) {
        this.warehouseAssistantPersistencePort = warehouseAssistantPersistencePort;
    }

    @Override
    public void saveWareHouseAssistant(WarehouseAssistant warehouseAssistant) {

        UserValidatorUtil.userFieldsValidated(warehouseAssistant);

        if (warehouseAssistantPersistencePort.existByIdentityDocument(warehouseAssistant.getIdentityDocument())) {
            throw new UserAlreadyExistException(
                    String.format(ValidationErrorConstants.USER_ALREADY_EXIST.getMessage(),
                            warehouseAssistant.getIdentityDocument()));
        }



        if (!warehouseAssistantPersistencePort.rolNameExist(RoleConstants.WAREHOUSE_ASSISTANT.name())) {
            throw new RoleNotFoundException(
                    String.format(ValidationErrorConstants.ROLE_NOT_FOUND.getMessage(),
                            RoleConstants.WAREHOUSE_ASSISTANT.name())
            );

        }

        warehouseAssistantPersistencePort.saveWarehouseAssistant(warehouseAssistant);

    }
}
