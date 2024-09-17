package com.emazon.emazonuserservice.configuration.security.dto;

import com.emazon.emazonuserservice.configuration.security.constants.ErrorMessageConstants;
import com.emazon.emazonuserservice.configuration.security.constants.SecurityConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsDto {

    @NotBlank(message= ErrorMessageConstants.FIELD_PASSWORD_EMPTY_OR_NULL)
    @NotNull(message = ErrorMessageConstants.FIELD_USERNAME_EMPTY_OR_NULL)
    private String username;
    @NotBlank(message = ErrorMessageConstants.FIELD_PASSWORD_EMPTY_OR_NULL)
    @NotNull(message = ErrorMessageConstants.FIELD_USERNAME_EMPTY_OR_NULL)
    private String password;
}
