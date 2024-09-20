package com.emazon.emazonuserservice.domain.constants;

public final class RegexConstants {

    private RegexConstants(){
        throw new IllegalStateException();
    }

    public static final String IDENTITY_DOCUMENT_REGEX = "^\\d+$";
    public static final String PHONE_NUMBER_REGEX = "^\\+?\\d{1,13}$";
    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$";


}