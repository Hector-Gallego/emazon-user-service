package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.ports.driving.dto.request.UserCredentialsRequestDto;
import com.emazon.emazonuserservice.factory.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @Mock
    private JwtTokenGeneratorService jwtTokenGeneratorService;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void shouldGenerateJwtTokenWhenUserIsAuthenticated() {

        UserCredentialsRequestDto userCredentials = TestDataFactory.createUserCredentials();
        String mockToken = "mockToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtTokenGeneratorService.generateJwtToken(authentication))
                .thenReturn(mockToken);


        String token = authenticationService.authenticatedUserAndGeneratedToken(
                        userCredentials.getUsername(),
                        userCredentials.getPassword());


        assertEquals(mockToken, token);
        verify(authenticationManager, times(1))
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userCredentials.getUsername(),
                                userCredentials.getPassword()));

        verify(jwtTokenGeneratorService, times(1)).generateJwtToken(authentication);
    }


    @Test
    void shouldThrowBadCredentialsExceptionWhenUserCredentialsAreInvalid() {

        UserCredentialsRequestDto userCredentials = TestDataFactory.createUserCredentials();
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));


        BadCredentialsException exception = assertThrows(
                BadCredentialsException.class,
                () -> authenticationService.authenticatedUserAndGeneratedToken(
                                userCredentials.getUsername(),
                                userCredentials.getPassword())
        );

        assertEquals("Invalid credentials", exception.getMessage());

        verify(authenticationManager, times(1))
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userCredentials.getUsername(),
                        userCredentials.getPassword()));

        verify(jwtTokenGeneratorService, never()).generateJwtToken(any(Authentication.class));
    }


}