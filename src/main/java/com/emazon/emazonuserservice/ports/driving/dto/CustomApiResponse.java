package com.emazon.emazonuserservice.ports.driving.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomApiResponse<T>{

    private Integer status;
    private String message;
    private T data;
    private LocalDateTime timestamp;
}