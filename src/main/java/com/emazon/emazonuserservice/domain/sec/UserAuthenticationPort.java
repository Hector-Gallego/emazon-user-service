package com.emazon.emazonuserservice.domain.sec;

public interface UserAuthenticationPort {

    void validateUsernameAndPassword(String username, String password);
    String getAuthenticatedUserAccessJwtToken(String username, String password);

}
