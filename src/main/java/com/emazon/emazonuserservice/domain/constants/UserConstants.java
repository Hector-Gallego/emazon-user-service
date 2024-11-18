package com.emazon.emazonuserservice.domain.constants;

public final class UserConstants {
    private UserConstants(){
        throw new IllegalStateException();
    }

    public static final String USER_CREATED_SUCCESSFULLY = "El usuario ha sido creado exitosamente.";
    public static final String USER_NOT_FOUND = "Usuario con %s no encontrado";
    public static final String USER_AUTHENTICATION_SUCCESSFULLY = "El usuario ha sido autenticado exitosamente.";
    public static final Integer MIN_USER_AGE = 18;

}
