package com.emazon.emazonuserservice.configuration.execptionhandler;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.exception.UserValidationException;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        List<String> errorList = exception
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS,
                errorList,
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                Collections.emptyList(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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