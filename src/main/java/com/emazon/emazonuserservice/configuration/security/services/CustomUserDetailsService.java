package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.configuration.security.config.CustomUserDetails;
import com.emazon.emazonuserservice.configuration.security.constants.ErrorMessageConstants;
import com.emazon.emazonuserservice.domain.ports.spi.UserPersistencePort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserPersistencePort userPersistencePort;

    public CustomUserDetailsService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return  userPersistencePort.findByEmail(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(ErrorMessageConstants.USER_NOT_FOUND, username)));

    }

}
