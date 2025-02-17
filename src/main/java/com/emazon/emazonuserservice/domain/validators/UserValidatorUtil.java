package com.emazon.emazonuserservice.domain.validators;

import com.emazon.emazonuserservice.domain.constants.UserConstants;
import com.emazon.emazonuserservice.domain.exception.UserValidationException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.constants.RegexConstants;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UserValidatorUtil {

    private UserValidatorUtil() {
        throw new IllegalStateException();
    }

    public static void userFieldsValidated(User user) {

        List<String> errors = new ArrayList<>();

        validatePhoneNumber(user.getPhoneNumber(), errors);
        validateEmail(user.getEmail(), errors);
        validateAge(user.getBirthDate(), errors);
        validateIdentityDocument(user.getIdentityDocument(), errors);
        validatePassword(user.getPassword(), errors);
        validateName(user.getName(), errors);
        validateLastName(user.getLastName(), errors);

        if (Boolean.FALSE.equals(errors.isEmpty())) {
            throw new UserValidationException(
                    ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS,
                    errors);
        }
    }

    private static void validateName(String username, List<String> erros){

        if (username == null || username.isBlank()){
            erros.add(ValidationErrorConstants.NULL_OR_EMPTY_NAME_FIELD);
        }

    }

    private static void validateLastName(String lastName, List<String> erros){

        if (lastName == null || lastName.isBlank()){
            erros.add(ValidationErrorConstants.NULL_OR_EMPTY_LAST_NAME_FIELD);
        }

    }

    private static void validatePassword(String password, List<String> errors) {


        if (password == null || password.isBlank()) {
            errors.add(ValidationErrorConstants.NULL_OR_EMPTY_PASSWORD_FIELD);
        } else if (Boolean.FALSE.equals(isValidField(RegexConstants.PASSWORD_REGEX, password))) {
            errors.add(ValidationErrorConstants.INVALID_PASSWORD);
        }

    }

    private static void validateEmail(String email, List<String> errors) {


        if (email.isBlank()) {
            errors.add(ValidationErrorConstants.NULL_OR_EMPTY_EMAIL_FIELD);
        } else if (Boolean.FALSE.equals(isValidField(RegexConstants.EMAIL_REGEX, email))) {
            errors.add(ValidationErrorConstants.INVALID_EMAIL);
        }
    }

    private static void validatePhoneNumber(String phoneNumber, List<String> errors) {

        if (phoneNumber == null || phoneNumber.isBlank()) {
            errors.add(ValidationErrorConstants.NULL_OR_EMPTY_PHONE_NUMBER_FIELD);
        } else if (Boolean.FALSE.equals(isValidField(RegexConstants.PHONE_NUMBER_REGEX, phoneNumber))) {
            errors.add(ValidationErrorConstants.INVALID_PHONE_NUMBER);
        }
    }

    private static void validateAge(LocalDate birthDate, List<String> errors) {

        if (birthDate == null) {
            errors.add(ValidationErrorConstants.NULL_OR_EMPTY_BIRTH_DATE_FIELD);
        } else {

            LocalDate today = LocalDate.now();
            Period age = Period.between(birthDate, today);
            if (age.getYears() < UserConstants.MIN_USER_AGE) {
                errors.add(ValidationErrorConstants.INVALID_AGE);
            }
        }

    }

    private static void validateIdentityDocument(String identityDocument, List<String> errors) {

        if (identityDocument == null || identityDocument.isBlank()) {
            errors.add(ValidationErrorConstants.NULL_OR_EMPTY_IDENTITY_DOCUMENT_FIELD);
        } else if (Boolean.FALSE.equals(isValidField(RegexConstants.IDENTITY_DOCUMENT_REGEX, identityDocument))) {
            errors.add(ValidationErrorConstants.INVALID_IDENTITY_DOCUMENT);
        }


    }


    public static Boolean isValidField(String regex, String field) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }

}
