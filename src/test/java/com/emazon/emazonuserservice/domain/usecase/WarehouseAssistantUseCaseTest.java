package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.exception.UserValidationException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.spi.IUserPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleConstants;
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
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private WarehouseAssistantUseCase warehouseAssistantUseCase;

    @Test
    void shouldSaveWarehouseAssistantSuccessfully() {

        User user = TestDataFactory.createValidUser();

        when(userPersistencePort.existByIdentityDocument(user.getIdentityDocument())).thenReturn(false);
        when(userPersistencePort.rolNameExist(RoleConstants.WAREHOUSE_ASSISTANT.name())).thenReturn(true);

        warehouseAssistantUseCase.saveWareHouseAssistant(user);

        verify(userPersistencePort, times(1)).saveUser(user);

    }


    @Test
    void shouldThrowValidationExceptionForInvalidFields() {


        User user = TestDataFactory.createInvalidUser();

        UserValidationException exception = assertThrows(UserValidationException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        //verificar que la funcione de guardar nunca se llama verify never

        assertEquals(ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS,
                exception.getMessage());


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


    }

    @Test
    void shouldThrowExceptionWhenUserRoleNotFound() {

        User user = TestDataFactory.createValidUser();

        when(userPersistencePort.rolNameExist(RoleConstants.WAREHOUSE_ASSISTANT.name())).thenReturn(false);

        RoleNotFoundException exception = assertThrows(RoleNotFoundException.class,
                () -> warehouseAssistantUseCase.saveWareHouseAssistant(user));

        assertEquals(String.format(ValidationErrorConstants.ROLE_NOT_FOUND,
                        RoleConstants.WAREHOUSE_ASSISTANT.name()),
                exception.getMessage());

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

    }



}