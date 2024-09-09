package com.emazon.emazonuserservice.domain.exception;

public class RoleNotFoundException extends RuntimeException{

    public RoleNotFoundException(String message){
        super(message);
    }
}
