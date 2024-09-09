package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.api.IUserServicePort;
import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.spi.IUserPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleConstants;
import com.emazon.emazonuserservice.domain.util.UserValidatorUtil;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;

public class WarehouseAssistantUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public WarehouseAssistantUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveWareHouseAssistant(User user) {

        UserValidatorUtil.userFieldsValidated(user);

        if (Boolean.TRUE.equals(userPersistencePort.existByIdentityDocument(user.getIdentityDocument()))) {
            throw new UserAlreadyExistException(
                    String.format(ValidationErrorConstants.USER_ALREADY_EXIST,
                            user.getIdentityDocument()));
        }



        if (Boolean.FALSE.equals(userPersistencePort.rolNameExist(RoleConstants.WAREHOUSE_ASSISTANT.name()))) {
            throw new RoleNotFoundException(
                    String.format(ValidationErrorConstants.ROLE_NOT_FOUND,
                            RoleConstants.WAREHOUSE_ASSISTANT.name())
            );

        }

        userPersistencePort.saveUser(user);



    }
}
