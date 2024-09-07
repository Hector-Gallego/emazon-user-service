package com.emazon.emazonuserservice.domain.util;

public enum RegexConstants {
    IDENTITY_DOCUMENT_REGEX("^\\d+$"),
    PHONE_NUMBER_REGEX("^\\+?\\d{1,13}$"),
    EMAIL_REGEX("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"),
    PASSWORD_REGEX("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$");

    private final String regex;

    RegexConstants(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return this.regex;
    }
}