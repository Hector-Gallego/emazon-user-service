package com.emazon.emazonuserservice.configuration.openapi.constants;

public final class OpenApiConstants {

    private OpenApiConstants(){
        throw new IllegalStateException();
    }


    public static final String OPENAPI_TITTLE = "Emazon user service API";
    public static final String OPENAPI_VERSION = "1.0";
    public static final String OPENAPI_DESCRIPTION = "API for the user microservice of the eAmazon e-commerce platform";

    public static final String OPENAPI_CODE_400 = "400";
    public static final String OPENAPI_CODE_500 = "500";
    public static final String OPENAPI_CODE_200 = "200";
    public static final String OPENAPI_MEDIA_TYPE_JSON = "application/json";

    public static final String OPENAPI_INTERNAL_SERVER_ERROR = "Internal server error";


    public static final String OPENAPI_CREATE_USER_SUMMARY = "Create a new user";
    public static final String OPENAPI_CREATE_USER_DESCRIPTION = "Creates a new user with the provided details";

    public static final String USER_CREATED = "User created successfully";
    public static final String INVALID_INPUT = "Invalid input";

    public static final String NAME_EXAMPLE = "Juan";
    public static final String LAST_NAME_EXAMPLE = "Gonzales";
    public static final String DATE_EXAMPLE = "01/01/2000";
    public static final String IDENTITY_DOCUMENT_EXAMPLE = "1001654123";
    public static final String PASSWORD_EXAMPLE = "Password123";
    public static final String PHONE_NUMBER_EXAMPLE = "+57567234";
    public static final String EMAIL_EXAMPLE = "juan@gmail.com";


    public static final String OPENAPI_AUTHENTICATION_USER_SUMMARY = "Authenticate a user to access the system.";
    public static final String OPENAPI_AUTHENTICATION_USER_DESCRIPTION = "Endpoint for user authentication. This endpoint allows users to authenticate themselves by providing valid credentials, which are then validated to grant access to the system.";
    public static final String USER_AUTHENTICATED_SUCCESSFULLY = "User has been successfully authenticated.";


}
