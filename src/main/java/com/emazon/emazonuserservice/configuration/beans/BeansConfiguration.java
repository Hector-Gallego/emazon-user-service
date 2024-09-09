package com.emazon.emazonuserservice.configuration.beans;

import com.emazon.emazonuserservice.domain.api.IUserServicePort;
import com.emazon.emazonuserservice.domain.spi.IUserPersistencePort;
import com.emazon.emazonuserservice.domain.usecase.WarehouseAssistantUseCase;
import com.emazon.emazonuserservice.ports.driven.adapter.UserJpaAdapter;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.IRoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserToUserEntityMapper userToUserEntityMapper;
    @Bean
    IUserServicePort userServicePort(IUserPersistencePort userPersistencePort){
        return new WarehouseAssistantUseCase(userPersistencePort);
    }


    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, roleRepository, passwordEncoder, userToUserEntityMapper);
    }


}
