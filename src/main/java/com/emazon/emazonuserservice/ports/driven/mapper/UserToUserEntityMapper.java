package com.emazon.emazonuserservice.ports.driven.mapper;


import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.ports.driven.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface UserToUserEntityMapper {

    UserEntity userToUserEntity(User user);
    User userEntityToUser(UserEntity userEntity);

}
