package com.emazon.emazonuserservice.ports.driven.adapter;

import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import com.emazon.emazonuserservice.domain.model.User;
import com.emazon.emazonuserservice.domain.spi.IUserPersistencePort;
import com.emazon.emazonuserservice.domain.util.RoleConstants;
import com.emazon.emazonuserservice.domain.util.ValidationErrorConstants;
import com.emazon.emazonuserservice.ports.driven.entity.RoleEntity;
import com.emazon.emazonuserservice.ports.driven.entity.UserEntity;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.IRoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserJpaAdapter implements IUserPersistencePort {


    private final IUserRepository warehouseAssistantRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserToUserEntityMapper userToUserEntityMapper;

    public UserJpaAdapter(IUserRepository warehouseAssistantRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder, UserToUserEntityMapper userToUserEntityMapper) {
        this.warehouseAssistantRepository = warehouseAssistantRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userToUserEntityMapper = userToUserEntityMapper;
    }


    @Override
    public void saveUser(User user) {

        RoleEntity roleEntity = roleRepository
                .findByName(RoleConstants.WAREHOUSE_ASSISTANT.name())
                .orElseThrow(() -> new RoleNotFoundException(
                        String.format(ValidationErrorConstants.ROLE_NOT_FOUND,
                                RoleConstants.WAREHOUSE_ASSISTANT)
                ));

        UserEntity assistantEntity = userToUserEntityMapper.userToUserEntity(user);

        //mover al caso de uso - crear un metodo en el puerto
        assistantEntity.setPassword(passwordEncoder.encode(assistantEntity.getPassword()));

        assistantEntity.setRole(roleEntity);
        warehouseAssistantRepository.save(assistantEntity);
    }

    @Override
    public Boolean existByIdentityDocument(String identityDocument) {
        return warehouseAssistantRepository.findByIdentityDocument(identityDocument).isPresent();
    }

    @Override
    public Boolean rolNameExist(String role) {
        return roleRepository.findByName(role).isPresent();
    }


}
