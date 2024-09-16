package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.util.UserConstants;
import com.emazon.emazonuserservice.factory.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAuthenticationUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;
    @Mock
    private JwtTokenGenerationPort tokenGenerationPort;

    @InjectMocks
    private UserAuthenticationUseCase userAuthenticationUseCase;


    @Test
    void shouldValidateUserCredentialsSuccessfully() {

        User user = TestDataFactory.createValidUser();

        when(userPersistencePort.validUserCredentials(user.getEmail(), user.getPassword())).thenReturn(true);

        userAuthenticationUseCase.validateUsernameAndPassword(user.getEmail(), user.getPassword());

        verify(userPersistencePort, times(1)).validUserCredentials(user.getEmail(), user.getPassword());
    }


    @Test
    void shouldThrowExceptionWhenUserCredentialsAreInvalid() {

        User user = TestDataFactory.createInvalidUser();


        when(userPersistencePort.validUserCredentials(user.getEmail(), user.getPassword())).thenReturn(false);

        InvalidUserCredentialsException exception = assertThrows(InvalidUserCredentialsException.class,
                () -> userAuthenticationUseCase.validateUsernameAndPassword(user.getEmail(), user.getPassword()));


        assertEquals(UserConstants.BAD_USER_CREDENTIALS_MESSAGE,
                exception.getMessage());


    }

    @Test
    void shouldGenerateJwtTokenForAuthenticatedUser() {

        User user = TestDataFactory.createValidUser();
        String expectedJwtToken = "generatedJwtToken";


        when(tokenGenerationPort.generateAccessJwtToken(user.getEmail(), user.getPassword())).thenReturn(expectedJwtToken);

        String jwtToken = userAuthenticationUseCase.getAuthenticatedUserAccessJwtToken(user.getEmail(), user.getPassword());
        assertEquals(expectedJwtToken, jwtToken);

        verify(tokenGenerationPort, times(1)).generateAccessJwtToken(user.getEmail(), user.getPassword());
    }

}