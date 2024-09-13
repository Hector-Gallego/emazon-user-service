package com.emazon.emazonuserservice.domain.util;

public final class UserConstants {
    private UserConstants(){
        throw new IllegalStateException();
    }

    public static final String USER_CREATED_SUCCESSFULLY = "User has been created successfully.";
    public static final String BAD_USER_CREDENTIALS_MESSAGE = "Invalid user credentials.";
    public static final String USER_AUTHENTICATION_SUCCESSFULLY = "User has been authenticated successfully.";
}
