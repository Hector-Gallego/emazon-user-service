package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.configuration.security.constants.ErrorMessageConstants;
import com.emazon.emazonuserservice.domain.exception.CredentialsInvalidFormatException;
import com.emazon.emazonuserservice.domain.ports.sec.AuthenticationSecurityPort;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLoginUseCaseTest {

    @Mock
    AuthenticationSecurityPort authenticationSecurityPort;

    @InjectMocks
    UserAuthenticationUseCase userLoginUseCase;

    @Test
    void shouldAuthenticateUserAndReturnJwtTokenWhenCredentialsAreValid(){

        String username = "user";
        String password = "password";
        String validToken = "valid token";

        when(authenticationSecurityPort.authenticatedUserAndGeneratedToken(username, password)).thenReturn(validToken);
        String tokenResult = userLoginUseCase.userAuthentication(username, password);
        assertEquals(validToken, tokenResult);
        verify(authenticationSecurityPort, times(1)).authenticatedUserAndGeneratedToken(username, password);


    }

    @Test
    void shouldWhenCredentialsInvalidFormatExceptionWhenUserCredentialsAreInvalid(){

        String username = "";
        String password = "";

        CredentialsInvalidFormatException exception = assertThrows(
                CredentialsInvalidFormatException.class,
                () -> userLoginUseCase.userAuthentication(username, password)
        );

        List<String> expectedErros = List.of(
                ErrorMessageConstants.FIELD_USERNAME_EMPTY_OR_NULL,
                ErrorMessageConstants.FIELD_PASSWORD_EMPTY_OR_NULL

        );

        assertEquals(expectedErros, exception.getErrors());
        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS, exception.getMessage());

        verify(authenticationSecurityPort, never()).authenticatedUserAndGeneratedToken(username, password);

    }


}