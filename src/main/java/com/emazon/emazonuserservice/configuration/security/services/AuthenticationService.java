package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.domain.ports.sec.AuthenticationSecurityPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


public class AuthenticationService implements AuthenticationSecurityPort {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenGeneratorService jwtTokenGeneratorService;

    public AuthenticationService(AuthenticationManager authenticationManager, JwtTokenGeneratorService jwtTokenGeneratorService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenGeneratorService = jwtTokenGeneratorService;
    }

    @Override
    public String authenticatedUserAndGeneratedToken(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        return jwtTokenGeneratorService.generateJwtToken(authentication);
    }


}
