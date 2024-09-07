package com.emazon.emazonuserservice.configuration.execptionhandler;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.exception.UserValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(UserValidationException.class)
    public ResponseEntity<ErrorResponse> handleUserValidationException(UserValidationException exception) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, exception.getErrors());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception exception, HttpStatus status, List<String> errors) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                exception.getMessage(),
                errors,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}