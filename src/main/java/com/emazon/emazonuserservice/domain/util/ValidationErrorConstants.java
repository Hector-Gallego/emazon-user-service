package com.emazon.emazonuserservice.domain.util;

public enum ValidationErrorConstants {

    INVALID_ONE_OR_MORE_FIELDS("One or more field are invalid"),
    INVALID_EMAIL("Invalid email format."),
    INVALID_PHONE_NUMBER("Invalid phone number format."),
    INVALID_AGE("User must be 18 years or older."),
    INVALID_IDENTITY_DOCUMENT("Identity document must be numeric."),
    NULL_OR_EMPTY_FIELD("Field cannot be empty or null."),
    INVALID_PASSWORD ("Invalid password. It must be at least 8 characters long," +
            "include at least one special character, one number, one uppercase letter," +
            " and one lowercase letter."),
    USER_ALREADY_EXIST("A user with teh identity document. %s already exist."),
    ROLE_NOT_FOUND("A role %s not found.");


    private final String message;
    ValidationErrorConstants(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
