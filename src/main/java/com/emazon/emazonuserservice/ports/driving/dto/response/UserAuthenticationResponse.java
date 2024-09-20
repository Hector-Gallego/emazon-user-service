package com.emazon.emazonuserservice.ports.driving.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class UserAuthenticationResponse {

    private Integer status;
    private String message;
    private String accessToken;
    private LocalDateTime timestamp;
}
