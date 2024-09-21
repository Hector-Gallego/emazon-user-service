package com.emazon.emazonuserservice.configuration.security.config;


import com.emazon.emazonuserservice.configuration.execptionhandler.DelegateAccessDeniedHandler;
import com.emazon.emazonuserservice.configuration.execptionhandler.DelegateAuthenticationEntryPoint;
import com.emazon.emazonuserservice.configuration.security.constants.ApiEndPointsConstants;
import com.emazon.emazonuserservice.configuration.security.constants.ClaimTokenConstants;
import com.emazon.emazonuserservice.configuration.security.constants.SecurityConstants;
import com.emazon.emazonuserservice.configuration.security.services.CustomUserDetailsService;
import com.emazon.emazonuserservice.domain.constants.RoleNameConstants;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final RsaKeyProperties rsaKeys;

    private final DelegateAuthenticationEntryPoint authEntryPoint;
    private final DelegateAccessDeniedHandler accessDeniedHandler;

    public SecurityConfig(RsaKeyProperties rsaKeys,
                          DelegateAuthenticationEntryPoint authEntryPoint,
                          DelegateAccessDeniedHandler accessDeniedHandler) {
        this.rsaKeys = rsaKeys;
        this.authEntryPoint = authEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                ApiEndPointsConstants.USER_LOGIN_URI,
                                ApiEndPointsConstants.REGISTRATION_CLIENT_URI)
                        .permitAll()

                        .requestMatchers(
                                ApiEndPointsConstants.REGISTRATION_WAREHOUSE_ASSISTANT_URI)
                        .hasAuthority(RoleNameConstants.ADMIN.name())

                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {
                            jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
                            jwt.decoder(jwtDecoder());
                        })
                        .authenticationEntryPoint(authEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(ClaimTokenConstants.CLAIM_NAME_FIELD_ROLE);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(SecurityConstants.BLANK_FIELD);

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;

    }

    @Bean
    public AuthenticationManager authManager(CustomUserDetailsService customUserDetailsService) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authProvider);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }

}