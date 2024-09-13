package com.emazon.emazonuserservice.domain.exception;

import com.emazon.emazonuserservice.domain.util.UserConstants;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException() {
        super(UserConstants.BAD_USER_CREDENTIALS_MESSAGE);
    }
}
