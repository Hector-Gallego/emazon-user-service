package com.emazon.emazonuserservice.domain.usecase;

import com.emazon.emazonuserservice.domain.model.Role;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.spi.IUserPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleConstants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WarehouseAssistantUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private WarehouseAssistantUseCase warehouseAssistantUseCase;

    @Test
    void saveWareHouseAssistantSuccess(){


        Role role = new Role();
        role.setName(RoleConstants.WAREHOUSE_ASSISTANT.name());

        User user = User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .identityDocument("123456789")
                .phoneNumber("555-555-5555")
                .birthDate(LocalDate.of(1990, 1, 1))
                .email("john.doe@example.com")
                .password("SecurePass123")
                .role(role)
                .build();

        when(userPersistencePort.existByIdentityDocument(user.getIdentityDocument())).thenReturn(false);
        when(userPersistencePort.rolNameExist(RoleConstants.WAREHOUSE_ASSISTANT.name())).thenReturn(true);

        warehouseAssistantUseCase.saveWareHouseAssistant(user);

        verify(userPersistencePort, times(1)).saveUser(user);

    }




}