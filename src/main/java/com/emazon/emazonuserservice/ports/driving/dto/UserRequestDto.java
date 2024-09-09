package com.emazon.emazonuserservice.ports.driving.dto;

import com.emazon.emazonuserservice.domain.util.RegexConstants;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    private String name;

    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    private String lastName;

    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Pattern(regexp = RegexConstants.IDENTITY_DOCUMENT_REGEX,
            message = ValidationErrorConstants.INVALID_IDENTITY_DOCUMENT)
    private String identityDocument;

    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Pattern(regexp = RegexConstants.PHONE_NUMBER_REGEX,
            message = ValidationErrorConstants.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = ValidationErrorConstants.DATE_PATTERN)
    private LocalDate birthDate;

    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Pattern(regexp = RegexConstants.EMAIL_REGEX,
            message = ValidationErrorConstants.INVALID_EMAIL)
    private String email;

    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Size(min = 8, message = ValidationErrorConstants.INVALID_EMAIL)
    @Pattern(regexp = RegexConstants.PASSWORD_REGEX,
            message = ValidationErrorConstants.INVALID_PASSWORD)
    private String password;

}