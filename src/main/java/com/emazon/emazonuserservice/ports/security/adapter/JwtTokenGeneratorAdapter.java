package com.emazon.emazonuserservice.ports.security.adapter;

import com.emazon.emazonuserservice.configuration.security.services.TokenGeneratorService;
import com.emazon.emazonuserservice.domain.sec.JwtTokenGenerationPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


public class JwtTokenGeneratorAdapter implements JwtTokenGenerationPort {

    private final AuthenticationManager authenticationManager;
    private final TokenGeneratorService jwtService;

    public JwtTokenGeneratorAdapter(AuthenticationManager authenticationManager, TokenGeneratorService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public String generateAccessJwtToken(String username, String password){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return jwtService.GenerateToken(authentication);
    }
}
