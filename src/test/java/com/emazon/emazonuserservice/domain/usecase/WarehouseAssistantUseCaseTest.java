package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.exception.UserValidationException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.ports.sec.PasswordEncoderPort;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleNameConstants;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;
import com.emazon.emazonuserservice.factory.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WarehouseAssistantUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    PasswordEncoderPort passwordEncoder;

    @InjectMocks
    private WarehouseAssistantUseCase warehouseAssistantUseCase;

    @Test
    void shouldSaveWarehouseAssistantSuccessfully() {

        String encodePassword = "Encode Password";
        User user = TestDataFactory.createValidUser();

        when(userPersistencePort.existByIdentityDocument(user.getIdentityDocument())).thenReturn(false);
        when(userPersistencePort.rolNameExist(RoleNameConstants.WAREHOUSE_ASSISTANT.name())).thenReturn(true);
        when(passwordEncoder.encodePassword(user.getPassword())).thenReturn(encodePassword);


        warehouseAssistantUseCase.saveWareHouseAssistant(user);

        verify(userPersistencePort, times(1)).saveUser(user, encodePassword);

    }


    @Test
    void shouldThrowValidationExceptionForInvalidFields() {


        User user = TestDataFactory.createInvalidUser();

        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));


        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS,
                exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());


    }

    @Test
    void shouldThrowExceptionWhenUserAlreadyExists() {


        User user = TestDataFactory.createValidUser();

        when(userPersistencePort.existByIdentityDocument(user.getIdentityDocument())).thenReturn(true);

        UserAlreadyExistException exception = assertThrows(UserAlreadyExistException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(String.format(ValidationErrorConstants.USER_ALREADY_EXIST,
                        user.getIdentityDocument()),
                exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());


    }

    @Test
    void shouldThrowExceptionWhenUserRoleNotFound() {

        User user = TestDataFactory.createValidUser();

        when(userPersistencePort.rolNameExist(RoleNameConstants.WAREHOUSE_ASSISTANT.name())).thenReturn(false);

        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(String.format(ValidationErrorConstants.ROLE_NOT_FOUND,
                        RoleNameConstants.WAREHOUSE_ASSISTANT.name()),
                exception.getMessage());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());

    }


    @Test
    void shouldThrowExceptionForInvalidEmail() {

        User user = TestDataFactory.createInvalidUSerEmail();

        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS, exception.getMessage());

        List<String> expectedErrors = List.of(
                ValidationErrorConstants.INVALID_EMAIL
        );
        assertEquals(expectedErrors, exception.getErrors());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());
    }


    @Test
    void shouldThrowExceptionForInvalidPhoneNumber() {

        User user = TestDataFactory.createInvalidUSerPhoneNumber();

        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS, exception.getMessage());

        List<String> expectedErrors = List.of(
                ValidationErrorConstants.INVALID_PHONE_NUMBER
        );
        assertEquals(expectedErrors, exception.getErrors());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());

    }

    @Test
    void shouldThrowExceptionForInvalidIdentityDocument() {

        User user = TestDataFactory.createInvalidUSerIdentityDocument();

        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS, exception.getMessage());

        List<String> expectedErrors = List.of(
                ValidationErrorConstants.INVALID_IDENTITY_DOCUMENT
        );
        assertEquals(expectedErrors, exception.getErrors());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());

    }

    @Test
    void shouldThrowExceptionForInvalidPassword() {

        User user = TestDataFactory.createInvalidUSerPassword();

        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS, exception.getMessage());

        List<String> expectedErrors = List.of(
                ValidationErrorConstants.INVALID_PASSWORD
        );
        assertEquals(expectedErrors, exception.getErrors());

        verify(userPersistencePort, never()).saveUser(any(User.class), anyString());

    }



}