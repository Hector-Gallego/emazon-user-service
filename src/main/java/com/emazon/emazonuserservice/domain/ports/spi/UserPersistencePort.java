package com.emazon.emazonuserservice.domain.ports.spi;

import com.emazon.emazonuserservice.domain.model.User;

import java.util.Optional;


public interface UserPersistencePort {

    void saveUser(User warehouseAssistant, String encodePassword);
    Boolean existByIdentityDocument(String identityDocument);
    Boolean rolNameExist(String role);
    Optional<User> findByEmail(String email);


}