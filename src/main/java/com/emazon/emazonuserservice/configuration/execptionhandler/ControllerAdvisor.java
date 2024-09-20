package com.emazon.emazonuserservice.configuration.execptionhandler;

import com.emazon.emazonuserservice.domain.exception.CredentialsInvalidFormatException;
import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.exception.UserAlreadyExistException;
import com.emazon.emazonuserservice.domain.exception.UserValidationException;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
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
    public ResponseEntity<CustomErrorResponse> handleUserValidationException(UserValidationException exception) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, exception.getErrors());
    }
    @ExceptionHandler(CredentialsInvalidFormatException.class)
    public ResponseEntity<CustomErrorResponse> handleCredentialsInvalidFormatException(CredentialsInvalidFormatException exception) {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, exception.getErrors());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleRoleNotFoundException(RoleNotFoundException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<CustomErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<CustomErrorResponse> handleDateTimeParseException(DateTimeParseException exception){
        return  buildErrorResponse(exception, HttpStatus.BAD_REQUEST, Collections.emptyList());
    }

    @ExceptionHandler(InvalidBearerTokenException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidBearerTokenException(InvalidBearerTokenException exception) {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, Collections.emptyList());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomErrorResponse> handleAccessDeniedException(AccessDeniedException exception){
        return  buildErrorResponse(exception, HttpStatus.FORBIDDEN, Collections.emptyList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentialsException(BadCredentialsException exception){
        return  buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, Collections.emptyList());
    }


    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleBadCredentialsException(InsufficientAuthenticationException exception){
        return  buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, Collections.emptyList());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntimeException(RuntimeException exception){

        return buildErrorResponse(exception,
                HttpStatus.INTERNAL_SERVER_ERROR, Collections.emptyList());

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

        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ValidationErrorConstants.INVALID_ONE_OR_MORE_FIELDS,
                errorList,
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @Nonnull HttpMessageNotReadableException ex,
            @Nullable HttpHeaders headers,
            @Nullable HttpStatusCode status,
            @Nullable WebRequest request) {

        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ValidationErrorConstants.INVALID_DATA_FORMAT,
                Collections.emptyList(),
                LocalDateTime.now()

        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    private ResponseEntity<CustomErrorResponse> buildErrorResponse(Exception exception, HttpStatus status, List<String> errors) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                status.value(),
                exception.getMessage(),
                errors,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(customErrorResponse, status);
    }
}