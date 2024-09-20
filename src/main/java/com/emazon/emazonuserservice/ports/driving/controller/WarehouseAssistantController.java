package com.emazon.emazonuserservice.ports.driving.controller;

import com.emazon.emazonuserservice.configuration.execptionhandler.CustomErrorResponse;
import com.emazon.emazonuserservice.domain.ports.api.UserServicePort;
import com.emazon.emazonuserservice.domain.constants.UserConstants;
import com.emazon.emazonuserservice.ports.driving.dto.response.UserRegisterResponse;
import com.emazon.emazonuserservice.ports.driving.dto.request.UserRequestDto;
import com.emazon.emazonuserservice.ports.driving.mapper.UserToUserDtoMapper;
import com.emazon.emazonuserservice.configuration.openapi.constants.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;



@RestController
@RequestMapping("/api/user/warehouseAssistant")
public class WarehouseAssistantController {

    private final UserServicePort userServicePort;
    private final UserToUserDtoMapper userToUserDtoMapper;

    public WarehouseAssistantController(UserServicePort userServicePort, UserToUserDtoMapper userToUserDtoMapper) {
        this.userServicePort = userServicePort;
        this.userToUserDtoMapper = userToUserDtoMapper;
    }



    @Operation(summary = OpenApiConstants.OPENAPI_CREATE_USER_SUMMARY,
            description = OpenApiConstants.OPENAPI_CREATE_USER_DESCRIPTION)

    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_200,
                    description = OpenApiConstants.USER_CREATED,
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
    public ResponseEntity<UserRegisterResponse> saveWarehouseAssistant(@RequestBody UserRequestDto userRequestDto){

        userServicePort.saveWareHouseAssistant(userToUserDtoMapper.userRequestDtoToDomain(userRequestDto));


        UserRegisterResponse response = new UserRegisterResponse(
                HttpStatus.OK.value(),
                UserConstants.USER_CREATED_SUCCESSFULLY,
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}