package com.emazon.emazonuserservice.domain.util;

public final class ValidationErrorConstants {

    private ValidationErrorConstants(){
        throw new IllegalStateException();
    }

    public static final String INVALID_ONE_OR_MORE_FIELDS = "One or more fields are invalid.";
    public static final String INVALID_EMAIL = "Invalid email format.";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number format.";
    public static final String INVALID_AGE = "User must be 18 years or older.";
    public static final String INVALID_IDENTITY_DOCUMENT = "Identity document must be numeric.";
    public static final String NULL_OR_EMPTY_FIELD = "Field cannot be empty or null.";
    public static final String INVALID_PASSWORD = "Invalid password. It must be at least 8 characters long, " +
            "include at least one special character, one number, one uppercase letter, " +
            "and one lowercase letter.";
    public static final String USER_ALREADY_EXIST = "A user with the identity document %s already exists.";
    public static final String ROLE_NOT_FOUND = "A role %s not found.";
    public static final String DATE_PATTERN = "dd/MM/yyyy";



}
