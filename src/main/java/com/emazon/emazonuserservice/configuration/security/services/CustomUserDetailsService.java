package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.configuration.security.config.CustomUserDetails;
import com.emazon.emazonuserservice.ports.driven.mapper.UserToUserEntityMapper;
import com.emazon.emazonuserservice.ports.driven.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserToUserEntityMapper userMapper;

    public CustomUserDetailsService(UserRepository userRepository, UserToUserEntityMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userRepository.findByEmail(username)
                .map(userMapper::userEntityToUser)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }


}
