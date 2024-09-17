package com.emazon.emazonuserservice.configuration.security.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenGeneratorService jwtTokenGeneratorService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenGeneratorService jwtTokenGeneratorService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenGeneratorService = jwtTokenGeneratorService;
    }

    public String authenticatedAndGenerateJwtToken(String username, String password){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return jwtTokenGeneratorService.generateJwtToken(authentication);
    }
}
