package com.emazon.emazonuserservice.domain.spi;

import com.emazon.emazonuserservice.domain.model.User;

public interface IUserPersistencePort {

    void saveUser(User warehouseAssistant);
    Boolean existByIdentityDocument(String identityDocument);
    Boolean rolNameExist(String role);



}