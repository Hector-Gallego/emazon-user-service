package com.emazon.emazonuserservice.ports.driving.dto.request;

import com.emazon.emazonuserservice.configuration.security.constants.ErrorMessageConstants;
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
public class UserCredentialsRequestDto {

    @NotBlank(message= ErrorMessageConstants.FIELD_PASSWORD_EMPTY_OR_NULL)
    @NotNull(message = ErrorMessageConstants.FIELD_USERNAME_EMPTY_OR_NULL)
    private String username;
    @NotBlank(message = ErrorMessageConstants.FIELD_PASSWORD_EMPTY_OR_NULL)
    @NotNull(message = ErrorMessageConstants.FIELD_USERNAME_EMPTY_OR_NULL)
    private String password;
}
