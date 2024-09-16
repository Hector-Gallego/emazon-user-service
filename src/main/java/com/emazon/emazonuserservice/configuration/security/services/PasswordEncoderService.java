package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.domain.ports.sec.PasswordEncoderPort;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordEncoderService implements PasswordEncoderPort {

    private final PasswordEncoder passwordEncoder;

    public PasswordEncoderService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}

