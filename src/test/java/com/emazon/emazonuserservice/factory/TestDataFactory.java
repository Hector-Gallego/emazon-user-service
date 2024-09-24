package com.emazon.emazonuserservice.factory;

import com.emazon.emazonuserservice.ports.driving.dto.request.UserCredentialsRequestDto;
import com.emazon.emazonuserservice.domain.model.Role;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.constants.RoleNameConstants;

import java.time.LocalDate;

public final class TestDataFactory {

    private TestDataFactory() {
        throw new IllegalStateException();
    }

    public static User createValidUser() {
        Role role = createWarehouseAssistantRole();
        return User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .identityDocument("123456789")
                .phoneNumber("+5555555")
                .birthDate(LocalDate.of(1990, 1, 1))
                .email("john.doe@example.com")
                .password("SecurePass123")
                .role(role)
                .build();
    }

    public static Role createWarehouseAssistantRole() {
        Role role = new Role();
        role.setName(RoleNameConstants.WAREHOUSE_ASSISTANT.name());
        return role;
    }

    public static User createInvalidUser() {
        return User.builder()
                .id(1L)
                .name("John")
                .lastName("Doe")
                .identityDocument("invalidDoc")
                .phoneNumber("+invalidPhone")
                .birthDate(LocalDate.of(1990, 1, 1))
                .email("invalidEmail")
                .password("invalidPassword")
                .role(createWarehouseAssistantRole())
                .build();
    }

    public static User createInvalidUSerEmail() {
        return User.builder()
                .id(createValidUser().getId())
                .name(createValidUser().getName())
                .lastName(createValidUser().getLastName())
                .identityDocument(createValidUser().getIdentityDocument())
                .phoneNumber(createValidUser().getPhoneNumber())
                .birthDate(createValidUser().getBirthDate())
                .email("invalidEmail")
                .password(createValidUser().getPassword())
                .role(createValidUser().getRole())
                .build();
    }

    public static User createInvalidUSerIdentityDocument() {
        return User.builder()
                .id(createValidUser().getId())
                .name(createValidUser().getName())
                .lastName(createValidUser().getLastName())
                .identityDocument("InvalidDocument")
                .phoneNumber(createValidUser().getPhoneNumber())
                .birthDate(createValidUser().getBirthDate())
                .email(createValidUser().getEmail())
                .password(createValidUser().getPassword())
                .role(createValidUser().getRole())
                .build();
    }

    public static User createInvalidUSerPassword() {
        return User.builder()
                .id(createValidUser().getId())
                .name(createValidUser().getName())
                .lastName(createValidUser().getLastName())
                .identityDocument(createValidUser().getIdentityDocument())
                .phoneNumber(createValidUser().getPhoneNumber())
                .birthDate(createValidUser().getBirthDate())
                .email(createValidUser().getEmail())
                .password("invalidPassword")
                .role(createValidUser().getRole())
                .build();
    }

    public static User createInvalidUSerName() {
        return User.builder()
                .id(createValidUser().getId())
                .name("")
                .lastName(createValidUser().getLastName())
                .identityDocument(createValidUser().getIdentityDocument())
                .phoneNumber(createValidUser().getPhoneNumber())
                .birthDate(createValidUser().getBirthDate())
                .email(createValidUser().getEmail())
                .password(createValidUser().getPassword())
                .role(createValidUser().getRole())
                .build();
    }

    public static User createInvalidUSerLastName() {
        return User.builder()
                .id(createValidUser().getId())
                .name(createValidUser().getName())
                .lastName("")
                .identityDocument(createValidUser().getIdentityDocument())
                .phoneNumber(createValidUser().getPhoneNumber())
                .birthDate(createValidUser().getBirthDate())
                .email(createValidUser().getEmail())
                .password(createValidUser().getPassword())
                .role(createValidUser().getRole())
                .build();
    }

    public static User createInvalidUSerPhoneNumber() {
        return User.builder()
                .id(createValidUser().getId())
                .name(createValidUser().getName())
                .lastName(createValidUser().getLastName())
                .identityDocument(createValidUser().getIdentityDocument())
                .phoneNumber("InvalidPhoneNumber")
                .birthDate(createValidUser().getBirthDate())
                .email(createValidUser().getEmail())
                .password(createValidUser().getPassword())
                .role(createValidUser().getRole())
                .build();
    }

    public static UserCredentialsRequestDto createUserCredentials() {
        return new UserCredentialsRequestDto("admin@gmail.com", "Admin1234");
    }


}
