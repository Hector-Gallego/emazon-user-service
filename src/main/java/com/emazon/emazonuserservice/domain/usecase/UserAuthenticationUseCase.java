package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.ports.api.UserAuthenticationServicePort;
import com.emazon.emazonuserservice.domain.ports.sec.AuthenticationSecurityPort;
import com.emazon.emazonuserservice.domain.validators.CredentialsValidatorUtil;

public class UserAuthenticationUseCase implements UserAuthenticationServicePort {

    private final AuthenticationSecurityPort securityPort;

    public UserAuthenticationUseCase(AuthenticationSecurityPort securityPort) {
        this.securityPort = securityPort;
    }

    @Override
    public String userAuthentication(String username, String password) {

        CredentialsValidatorUtil.userCredentialsFieldsValidated(username, password);
        return securityPort.authenticatedUserAndGeneratedToken(username, password);

    }
}
