package com.emazon.emazonuserservice.configuration.security.constants;

public final class ClaimTokenConstants {

    private ClaimTokenConstants(){
        throw new IllegalStateException();
    }
    public static final String CLAIM_NAME_FIELD_USERID = "userId";
    public static final String CLAIM_NAME_FIELD_NAME = "name";
    public static final String CLAIM_NAME_FIELD_ROLE = "role";
}
