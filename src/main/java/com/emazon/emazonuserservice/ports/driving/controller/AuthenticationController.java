package com.emazon.emazonuserservice.ports.driving.controller;


import com.emazon.emazonuserservice.configuration.execptionhandler.CustomErrorResponse;
import com.emazon.emazonuserservice.ports.driving.dto.response.UserAuthenticationResponse;
import com.emazon.emazonuserservice.domain.ports.api.UserAuthenticationServicePort;
import com.emazon.emazonuserservice.domain.constants.UserConstants;
import com.emazon.emazonuserservice.ports.driving.dto.response.UserRegisterResponse;
import com.emazon.emazonuserservice.ports.driving.dto.request.UserCredentialsRequestDto;
import com.emazon.emazonuserservice.configuration.openapi.constants.OpenApiConstants;
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
public class AuthenticationController {


    private final UserAuthenticationServicePort userAuthenticationServicePort;

    public AuthenticationController(UserAuthenticationServicePort userAuthenticationServicePort) {
        this.userAuthenticationServicePort = userAuthenticationServicePort;
    }

    @Operation(summary = OpenApiConstants.OPENAPI_AUTHENTICATION_USER_SUMMARY,
            description = OpenApiConstants.OPENAPI_AUTHENTICATION_USER_DESCRIPTION)

    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_200,
                    description = OpenApiConstants.USER_AUTHENTICATED_SUCCESSFULLY,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = UserRegisterResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_400,
                    description = OpenApiConstants.INVALID_INPUT,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_500,
                    description = OpenApiConstants.OPENAPI_INTERNAL_SERVER_ERROR,
                    content = @Content(mediaType = OpenApiConstants.OPENAPI_MEDIA_TYPE_JSON,
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<UserAuthenticationResponse> loginUser(@Validated @RequestBody UserCredentialsRequestDto userCredentialsRequestDto) {


        String accessToken = userAuthenticationServicePort
                .userAuthentication(userCredentialsRequestDto.getUsername(),
                        userCredentialsRequestDto.getPassword());


        UserAuthenticationResponse response = new UserAuthenticationResponse(
                HttpStatus.OK.value(),
                UserConstants.USER_AUTHENTICATION_SUCCESSFULLY,
                accessToken,
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}


