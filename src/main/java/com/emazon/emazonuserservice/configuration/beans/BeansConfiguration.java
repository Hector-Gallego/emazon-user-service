package com.emazon.emazonuserservice.configuration.beans;

import com.emazon.emazonuserservice.configuration.security.services.TokenGeneratorService;
import com.emazon.emazonuserservice.domain.api.UserServicePort;
import com.emazon.emazonuserservice.domain.sec.JwtTokenGenerationPort;
import com.emazon.emazonuserservice.domain.sec.PasswordEncoderPort;
import com.emazon.emazonuserservice.domain.sec.UserAuthenticationPort;
import com.emazon.emazonuserservice.domain.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.usecase.UserAuthenticationUseCase;
import com.emazon.emazonuserservice.domain.usecase.WarehouseAssistantUseCase;
import com.emazon.emazonuserservice.ports.driven.adapter.UserJpaAdapter;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.RoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.UserRepository;
import com.emazon.emazonuserservice.ports.security.adapter.JwtTokenGeneratorAdapter;
import com.emazon.emazonuserservice.ports.security.adapter.PasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {


    private final UserToUserEntityMapper userToUserEntityMapper;


    @Bean
    public PasswordEncoderPort passwordEncoderPort(PasswordEncoder passwordEncoder){
        return new PasswordEncoderAdapter(passwordEncoder);
    }

    @Bean
    public UserPersistencePort userPersistencePort(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoderPort passwordEncoderPort ){
        return new UserJpaAdapter(userRepository, roleRepository, passwordEncoderPort, userToUserEntityMapper);
    }

    @Bean
    public UserAuthenticationPort authenticationUserPort(UserPersistencePort userPersistencePort, JwtTokenGenerationPort tokenGenerationPort){
        return new UserAuthenticationUseCase(userPersistencePort, tokenGenerationPort);
    }

    @Bean
    UserServicePort userServicePort(UserPersistencePort userPersistencePort, PasswordEncoderPort passwordEncoder){
        return new WarehouseAssistantUseCase(userPersistencePort, passwordEncoder);
    }

    @Bean
    public JwtTokenGenerationPort jwtTokenGenerationPort(AuthenticationManager authenticationManager, TokenGeneratorService tokenGeneratorService){
        return new JwtTokenGeneratorAdapter(authenticationManager,tokenGeneratorService);
    }


}