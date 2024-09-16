package com.emazon.emazonuserservice.configuration.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenGeneratorService tokenGeneratorService;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenGeneratorService tokenGeneratorService) {
        this.authenticationManager = authenticationManager;
        this.tokenGeneratorService = tokenGeneratorService;
    }

    public String authenticatedAndGenerateJwtToken(String username, String password){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return tokenGeneratorService.GenerateToken(authentication);
    }
}
