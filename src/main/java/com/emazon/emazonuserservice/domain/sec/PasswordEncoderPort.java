package com.emazon.emazonuserservice.domain.sec;

public interface PasswordEncoderPort {
    String encodePassword(String password);
    Boolean matchesPassword(String rawPassword, String encodePassword);
}
