package com.emazon.emazonuserservice.ports.driven.adapter;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.constants.RoleNameConstants;
import com.emazon.emazonuserservice.domain.constants.ValidationErrorConstants;
import com.emazon.emazonuserservice.ports.driven.entity.RoleEntity;
import com.emazon.emazonuserservice.ports.driven.entity.UserEntity;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.RoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.UserRepository;

import java.util.Optional;

public class UserJpaAdapter implements UserPersistencePort {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserToUserEntityMapper userToUserEntityMapper;

    public UserJpaAdapter(UserRepository userRepository, RoleRepository roleRepository, UserToUserEntityMapper userToUserEntityMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userToUserEntityMapper = userToUserEntityMapper;
    }


    @Override
    public void saveUser(User user, String encodePassword) {

        RoleEntity roleEntity = roleRepository
                .findByName(RoleNameConstants.WAREHOUSE_ASSISTANT.name())
                .orElseThrow(() -> new RoleNotFoundException(
                        String.format(ValidationErrorConstants.ROLE_NOT_FOUND,
                                RoleNameConstants.WAREHOUSE_ASSISTANT)
                ));

        UserEntity assistantEntity = userToUserEntityMapper.userToUserEntity(user);
        assistantEntity.setPassword(encodePassword);

        assistantEntity.setRole(roleEntity);
        userRepository.save(assistantEntity);
    }

    @Override
    public Boolean existByIdentityDocument(String identityDocument) {
        return userRepository.findByIdentityDocument(identityDocument).isPresent();
    }

    @Override
    public Boolean rolNameExist(String role) {
        return roleRepository.findByName(role).isPresent();
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userToUserEntityMapper::userEntityToUser);
    }

}
