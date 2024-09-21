package com.emazon.emazonuserservice.domain.ports.api;

import com.emazon.emazonuserservice.domain.model.User;

public interface UserServicePort {
    void saveWareHouseAssistant(User user);
    void saveClient(User user);
}
