package com.emazon.emazonuserservice.domain.validators;

import com.emazon.emazonuserservice.configuration.security.constants.ErrorMessageConstants;
import com.emazon.emazonuserservice.domain.exception.CredentialsInvalidFormatException;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;

import java.util.ArrayList;
import java.util.List;

public class CredentialsValidatorUtil {

    private CredentialsValidatorUtil(){
        throw new IllegalStateException();
    }

    public static void userCredentialsFieldsValidated(String username, String password) {

        List<String> errors = new ArrayList<>();

        validateUsername(username, errors);
        validatePassword(password, errors);

        if (Boolean.FALSE.equals(errors.isEmpty())) {
            throw new CredentialsInvalidFormatException(
                    ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS,
                    errors);
        }
    }

    private static void validatePassword(String password, List<String> errors) {

        if (password == null || password.isBlank()) {
            errors.add(ErrorMessageConstants.FIELD_PASSWORD_EMPTY_OR_NULL);
        }
    }

    private static void validateUsername(String email, List<String> errors) {

        if (email.isBlank()) {
            errors.add(ErrorMessageConstants.FIELD_USERNAME_EMPTY_OR_NULL);
        }
    }

}
