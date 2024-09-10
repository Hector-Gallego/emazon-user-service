package com.emazon.emazonuserservice.ports.driving.controller;

import com.emazon.emazonuserservice.configuration.execptionhandler.ErrorResponse;
import com.emazon.emazonuserservice.domain.api.IUserServicePort;
import com.emazon.emazonuserservice.domain.util.UserConstants;
import com.emazon.emazonuserservice.ports.driving.dto.CustomApiResponse;
import com.emazon.emazonuserservice.ports.driving.dto.UserRequestDto;
import com.emazon.emazonuserservice.ports.driving.mapper.UserToUserDtoMapper;
import com.emazon.emazonuserservice.ports.util.OpenApiConstants;
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
@RequestMapping("/api/user/warehouseAssistant/")
public class WarehouseAssistantController {

    private final IUserServicePort userServicePort;
    private final UserToUserDtoMapper userToUserDtoMapper;

    public WarehouseAssistantController(IUserServicePort userServicePort, UserToUserDtoMapper userToUserDtoMapper) {
        this.userServicePort = userServicePort;
        this.userToUserDtoMapper = userToUserDtoMapper;
    }



    @Operation(summary = OpenApiConstants.OPENAPI_CREATE_USER_SUMMARY,
            description = OpenApiConstants.OPENAPI_CREATE_USER_DESCRIPTION)

    @ApiResponses(value = {
            @ApiResponse(responseCode = OpenApiConstants.OPENAPI_CODE_200,
                    description = OpenApiConstants.USER_CREATED,
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
    public ResponseEntity<CustomApiResponse<Void>> saveWarehouseAssistant(@RequestBody UserRequestDto userRequestDto){

        userServicePort.saveWareHouseAssistant(userToUserDtoMapper.userRequestDtoToDomain(userRequestDto));


        CustomApiResponse<Void> response = new CustomApiResponse<>(
                HttpStatus.OK.value(),
                UserConstants.USER_CREATED_SUCCESSFULLY,
                null,
                LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}