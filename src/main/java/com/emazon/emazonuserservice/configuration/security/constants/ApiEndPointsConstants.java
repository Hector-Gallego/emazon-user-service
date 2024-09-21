package com.emazon.emazonuserservice.configuration.security.constants;

public final class ApiEndPointsConstants {



    private ApiEndPointsConstants(){
        throw new IllegalStateException();
    }

    public static final String USER_LOGIN_URI = "/api/user/login";
    public static final String REGISTRATION_WAREHOUSE_ASSISTANT_URI = "/api/user/warehouseAssistant";
    public static final String REGISTRATION_CLIENT_URI = "/api/user/client";


}
