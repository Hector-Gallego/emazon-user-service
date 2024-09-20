package com.emazon.emazonuserservice.domain.ports.sec;

public interface AuthenticationSecurityPort {

    String authenticatedUserAndGeneratedToken(String username, String password);
}
