package com.emazon.emazonuserservice.domain.constants;

public final class UserConstants {
    private UserConstants(){
        throw new IllegalStateException();
    }

    public static final String USER_CREATED_SUCCESSFULLY = "User has been created successfully.";
    public static final String USER_NOT_FOUND = "User whit %s not found";
    public static final String USER_AUTHENTICATION_SUCCESSFULLY = "User has been authenticated successfully.";
}
