package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.exception.InvalidUserCredentialsException;
import com.emazon.emazonuserservice.domain.sec.UserAuthenticationPort;
import com.emazon.emazonuserservice.domain.sec.JwtTokenGenerationPort;
import com.emazon.emazonuserservice.domain.spi.UserPersistencePort;

public class UserAuthenticationUseCase implements UserAuthenticationPort {

    private final UserPersistencePort userPersistencePort;
    private final JwtTokenGenerationPort tokenGenerationPort;

    public UserAuthenticationUseCase(UserPersistencePort userPersistencePort, JwtTokenGenerationPort tokenGenerationPort) {
        this.userPersistencePort = userPersistencePort;
        this.tokenGenerationPort = tokenGenerationPort;
    }


    @Override
    public void validateUsernameAndPassword(String username, String password) {

        if (Boolean.FALSE.equals(userPersistencePort.
                validUserCredentials(username, password))) {
            throw new InvalidUserCredentialsException();
        }
    }

    @Override
    public String getAuthenticatedUserAccessJwtToken(String username, String password) {
        return tokenGenerationPort.generateAccessJwtToken(username, password);
    }


}
