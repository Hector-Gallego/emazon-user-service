package com.emazon.emazonuserservice.ports.driving.controller;


import com.emazon.emazonuserservice.configuration.execptionhandler.ErrorResponse;
import com.emazon.emazonuserservice.domain.sec.UserAuthenticationPort;
import com.emazon.emazonuserservice.domain.util.UserConstants;
import com.emazon.emazonuserservice.ports.driving.dto.CustomApiResponse;
import com.emazon.emazonuserservice.ports.driving.dto.UserCredentialsDto;
import com.emazon.emazonuserservice.ports.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user/login")
public class UserAuthenticationController {


    private final UserAuthenticationPort authenticationUserPort;

    public UserAuthenticationController(UserAuthenticationPort authenticationUserPort) {
        this.authenticationUserPort = authenticationUserPort;
    }


    @Operation(summary = OpenApiConstants.OPENAPI_AUTHENTICATION_USER_SUMMARY,
            description = OpenApiConstants.OPENAPI_AUTHENTICATION_USER_DESCRIPTION)

    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_200,
                    description = OpenApiConstants.USER_AUTHENTICATED_SUCCESSFULLY,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomApiResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_400,
                    description = OpenApiConstants.INVALID_INPUT,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_500,
                    description = OpenApiConstants.OPENAPI_INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<CustomApiResponse<String>> loginUser(@Validated @RequestBody UserCredentialsDto userCredentialsDto) {


        String username = userCredentialsDto.getUsername();
        String password = userCredentialsDto.getPassword();


        authenticationUserPort.validateUsernameAndPassword(username, password);
        String accessToken = authenticationUserPort.getAuthenticatedUserAccessJwtToken(username, password);

        CustomApiResponse<String> response = new CustomApiResponse<>(
                HttpStatus.OK.value(),
                UserConstants.USER_AUTHENTICATION_SUCCESSFULLY,
                accessToken,
                LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}


