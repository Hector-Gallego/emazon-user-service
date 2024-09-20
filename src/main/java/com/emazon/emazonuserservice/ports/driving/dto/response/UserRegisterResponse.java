package com.emazon.emazonuserservice.ports.driving.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegisterResponse{
    private Integer status;
    private String message;
    private LocalDateTime timestamp;
}