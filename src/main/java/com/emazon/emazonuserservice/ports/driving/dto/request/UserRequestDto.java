package com.emazon.emazonuserservice.ports.driving.dto.request;

import com.emazon.emazonuserservice.domain.constants.RegexConstants;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;
import com.emazon.emazonuserservice.configuration.openapi.constants.OpenApiConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {

    @Schema(example = OpenApiConstants.NAME_EXAMPLE)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    private String name;

    @Schema(example = OpenApiConstants.LAST_NAME_EXAMPLE)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    private String lastName;

    @Schema(example = OpenApiConstants.IDENTITY_DOCUMENT_EXAMPLE)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Pattern(regexp = RegexConstants.IDENTITY_DOCUMENT_REGEX,
            message = ValidationErrorConstants.INVALID_IDENTITY_DOCUMENT)
    private String identityDocument;

    @Schema(example = OpenApiConstants.PHONE_NUMBER_EXAMPLE)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Pattern(regexp = RegexConstants.PHONE_NUMBER_REGEX,
            message = ValidationErrorConstants.INVALID_PHONE_NUMBER)
    private String phoneNumber;

    @Schema(example = OpenApiConstants.DATE_EXAMPLE)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @JsonFormat(shape = JsonFormat.Shape.STRING,
            pattern = ValidationErrorConstants.DATE_PATTERN)
    private LocalDate birthDate;

    @Schema(example = OpenApiConstants.EMAIL_EXAMPLE)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Pattern(regexp = RegexConstants.EMAIL_REGEX,
            message = ValidationErrorConstants.INVALID_EMAIL)
    private String email;

    @Schema(example = OpenApiConstants.PASSWORD_EXAMPLE)
    @NotBlank(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @NotNull(message = ValidationErrorConstants.NULL_OR_EMPTY_FIELD)
    @Size(min = 8, message = ValidationErrorConstants.INVALID_EMAIL)
    @Pattern(regexp = RegexConstants.PASSWORD_REGEX,
            message = ValidationErrorConstants.INVALID_PASSWORD)
    private String password;

}