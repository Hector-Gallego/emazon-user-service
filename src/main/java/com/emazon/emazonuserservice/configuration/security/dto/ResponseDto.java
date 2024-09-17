package com.emazon.emazonuserservice.configuration.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class ResponseDto {

    private Integer status;
    private String message;
    private String accessToken;
    private LocalDateTime timestamp;
}
