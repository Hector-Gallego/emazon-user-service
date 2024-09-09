package com.emazon.emazonuserservice.domain.exception;

import java.util.List;

public class UserValidationException extends RuntimeException{
    private final List<String> errors;
    public UserValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors;

    }
    public List<String> getErrors() {
        return errors;
    }
}