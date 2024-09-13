package com.emazon.emazonuserservice.domain.sec;

public interface JwtTokenGenerationPort {
    String generateAccessJwtToken(String username, String password);
}
