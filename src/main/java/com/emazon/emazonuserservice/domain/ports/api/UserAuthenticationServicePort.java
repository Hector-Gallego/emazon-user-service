package com.emazon.emazonuserservice.domain.ports.api;

public interface UserAuthenticationServicePort {
    String userAuthentication(String username, String password);
}
