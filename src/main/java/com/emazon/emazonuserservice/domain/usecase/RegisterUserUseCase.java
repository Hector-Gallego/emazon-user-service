package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.ports.api.UserServicePort;
import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.ports.sec.PasswordEncoderPort;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.constants.RoleNameConstants;
import com.emazon.emazonuserservice.domain.validators.UserValidatorUtil;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;

public class RegisterUserUseCase implements UserServicePort {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoderPort passwordEncoder;

    public RegisterUserUseCase(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoder) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveWareHouseAssistant(User user) {
        registerUserByRole(user, RoleNameConstants.WAREHOUSE_ASSISTANT.name());
    }

    @Override
    public void saveClient(User user) {
        registerUserByRole(user, RoleNameConstants.CLIENT.name());
    }


    private void registerUserByRole (User user, String role){

        UserValidatorUtil.userFieldsValidated(user);

        if (Boolean.TRUE.equals(userPersistencePort.existByIdentityDocument(user.getIdentityDocument()))) {
            throw new UserAlreadyExistException(
                    String.format(ValidationErrorConstants.USER_ALREADY_EXIST,
                            user.getIdentityDocument()));
        }

        if (Boolean.FALSE.equals(userPersistencePort.rolNameExist(role))) {
            throw new RoleNotFoundException(
                    String.format(ValidationErrorConstants.ROLE_NOT_FOUND, role)
            );
        }

        String encodePassword = passwordEncoder.encodePassword(user.getPassword());
        userPersistencePort.saveUser(user, encodePassword, role);

    }
}
