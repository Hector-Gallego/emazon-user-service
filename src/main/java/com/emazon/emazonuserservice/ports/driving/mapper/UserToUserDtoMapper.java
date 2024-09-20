package com.emazon.emazonuserservice.ports.driving.mapper;

import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.ports.driving.dto.request.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserToUserDtoMapper {
     User userRequestDtoToDomain(UserRequestDto userRequestDto);
}