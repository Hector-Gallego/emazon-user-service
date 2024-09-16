package com.emazon.emazonuserservice.configuration.beans;

import com.emazon.emazonuserservice.domain.ports.api.UserServicePort;
import com.emazon.emazonuserservice.domain.ports.sec.PasswordEncoderPort;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.usecase.WarehouseAssistantUseCase;
import com.emazon.emazonuserservice.ports.driven.adapter.UserJpaAdapter;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.RoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.UserRepository;
import com.emazon.emazonuserservice.configuration.security.services.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {


    private final UserToUserEntityMapper userToUserEntityMapper;


    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoder passwordEncoder){
        return new PasswordEncoderService(passwordEncoder);
    }

    @Bean
    public UserPersistencePort userPersistencePort(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoderPort passwordEncoderPort ){
        return new UserJpaAdapter(userRepository, roleRepository, userToUserEntityMapper);
    }


    @Bean
    UserServicePort userServicePort(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoder){
        return new WarehouseAssistantUseCase(userPersistencePort, passwordEncoder);
    }



}