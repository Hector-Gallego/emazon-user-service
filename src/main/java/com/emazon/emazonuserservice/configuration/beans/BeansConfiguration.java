package com.emazon.emazonuserservice.configuration.beans;

import com.emazon.emazonuserservice.configuration.security.services.AuthenticationService;
import com.emazon.emazonuserservice.configuration.security.services.JwtTokenGeneratorService;
import com.emazon.emazonuserservice.domain.ports.api.UserAuthenticationServicePort;
import com.emazon.emazonuserservice.domain.ports.api.UserServicePort;
import com.emazon.emazonuserservice.domain.ports.sec.PasswordEncoderPort;
import com.emazon.emazonuserservice.domain.ports.sec.AuthenticationSecurityPort;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import com.emazon.emazonuserservice.domain.usecase.UserAuthenticationUseCase;
import com.emazon.emazonuserservice.domain.usecase.RegisterUserUseCase;
import com.emazon.emazonuserservice.ports.driven.adapter.UserJpaAdapter;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.RoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.UserRepository;
import com.emazon.emazonuserservice.configuration.security.services.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

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
        return new RegisterUserUseCase(userPersistencePort, passwordEncoder);
    }

    @Bean
    AuthenticationSecurityPort userLoginSecurityPort(AuthenticationManager authenticationManager, JwtTokenGeneratorService jwtTokenGeneratorService){
        return new AuthenticationService(authenticationManager, jwtTokenGeneratorService);
    }

    @Bean
    UserAuthenticationServicePort userLoginServicePort(AuthenticationSecurityPort authenticationSecurityPort){
        return new UserAuthenticationUseCase(authenticationSecurityPort);
    }

    @Bean
    JwtTokenGeneratorService jwtTokenGeneratorService(JwtEncoder jwtEncoder){
        return new JwtTokenGeneratorService(jwtEncoder);
    }

}