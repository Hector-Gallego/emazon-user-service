package com.emazon.emazonuserservice.domain.ports.sec;

public interface PasswordEncoderPort {
    String encodePassword(String password);

}
