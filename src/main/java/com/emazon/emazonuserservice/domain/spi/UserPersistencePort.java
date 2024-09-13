package com.emazon.emazonuserservice.domain.spi;

import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.ports.driven.entity.UserEntity;

import java.util.Optional;

public interface UserPersistencePort {

    void saveUser(User warehouseAssistant, String encodePassword);
    Boolean existByIdentityDocument(String identityDocument);
    Boolean rolNameExist(String role);
    Boolean validUserCredentials(String username, String password);
    Optional<UserEntity> findByEmail(String email);


}