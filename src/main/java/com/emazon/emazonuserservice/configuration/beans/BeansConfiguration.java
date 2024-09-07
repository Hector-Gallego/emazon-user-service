package com.emazon.emazonuserservice.configuration.beans;

import com.emazon.emazonuserservice.domain.api.IWarehouseAssistantServicePort;
import com.emazon.emazonuserservice.domain.spi.IWarehouseAssistantPersistencePort;
import com.emazon.emazonuserservice.domain.usecase.WarehouseAssistantUseCase;
import com.emazon.emazonuserservice.ports.driven.adapter.WarehouseAssistantJpaAdapter;
import com.emazon.emazonuserservice.ports.driven.mapper.WarehouseAssistantEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.IRoleRepository;
import com.emazon.emazonuserservice.ports.driven.repository.IWarehouseAssistantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfiguration {

    private final IWarehouseAssistantRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Bean
    IWarehouseAssistantServicePort userServicePort(IWarehouseAssistantPersistencePort userPersistencePort){
        return new WarehouseAssistantUseCase(userPersistencePort);
    }


    @Bean
    public IWarehouseAssistantPersistencePort userPersistencePort(){
        return new WarehouseAssistantJpaAdapter(userRepository, roleRepository, passwordEncoder);
    }


}
