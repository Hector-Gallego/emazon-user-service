package com.emazon.emazonuserservice.domain.constants;

public final class ValidationErrorConstants {


    private ValidationErrorConstants(){
        throw new IllegalStateException();
    }

    public static final String INVALID_DATA_FORMAT = "Formato de datos inválido detectado. Asegúrate de que todos los campos estén correctamente formateados según los requisitos especificados.";
    public static final String INVALID_ONE_OR_MORE_FIELDS = "Uno o más campos son inválidos.";
    public static final String INVALID_EMAIL = "Formato de correo electrónico inválido.";
    public static final String INVALID_PHONE_NUMBER = "Formato de número de teléfono inválido.";
    public static final String INVALID_AGE = "El usuario debe tener 18 años o más.";
    public static final String INVALID_IDENTITY_DOCUMENT = "El documento de identidad debe ser numérico.";
    public static final String NULL_OR_EMPTY_FIELD = "El campo no puede estar vacío o nulo.";
    public static final String INVALID_PASSWORD = "Contraseña inválida. Debe tener al menos 8 caracteres, incluir al menos un número, una letra mayúscula y una letra minúscula.";
    public static final String USER_ALREADY_EXIST = "Ya existe un usuario con el documento de identidad %s.";
    public static final String ROLE_NOT_FOUND = "El rol %s no se encontró.";
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    public static final String NULL_OR_EMPTY_NAME_FIELD = "El campo nombre no puede estar vacío o nulo.";
    public static final String NULL_OR_EMPTY_LAST_NAME_FIELD = "El campo apellido no puede estar vacío o nulo.";
    public static final String NULL_OR_EMPTY_IDENTITY_DOCUMENT_FIELD = "El campo documento de identidad no puede estar vacío o nulo.";
    public static final String NULL_OR_EMPTY_PHONE_NUMBER_FIELD = "El campo número de teléfono no puede estar vacío o nulo.";
    public static final String NULL_OR_EMPTY_BIRTH_DATE_FIELD = "El campo fecha de nacimiento no puede estar vacío o nulo.";
    public static final String NULL_OR_EMPTY_EMAIL_FIELD = "El campo correo electrónico no puede estar vacío o nulo.";
    public static final String NULL_OR_EMPTY_PASSWORD_FIELD = "El campo contraseña no puede estar vacío o nulo.";






}
