package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.configuration.security.config.CustomUserDetails;
import com.emazon.emazonuserservice.configuration.security.util.SecurityConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenGeneratorService {

    @Value("${jwt.time.expiration}")
    private Integer timeExpiration;

    private final JwtEncoder tokenEncoder;
    public TokenGeneratorService(JwtEncoder tokenEncoder) {
        this.tokenEncoder = tokenEncoder;
    }

    public String GenerateToken(Authentication authentication){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Instant now = Instant.now();

        String roles = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(SecurityConstants.SPACE_FILED));


        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(SecurityConstants.CLAIM_NAME_FIELD_USERID, userDetails.getId());
        claimsMap.put(SecurityConstants.CLAIM_NAME_FIELD_NAME, userDetails.getName());
        claimsMap.put(SecurityConstants.CLAIM_NAME_FIELD_ROLES, roles);

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
                .builder()
                .subject(authentication.getName())
                .issuer(SecurityConstants.URL_APP)
                .expiresAt(now.plus(timeExpiration, ChronoUnit.MINUTES))
                .claims(claims -> claims.putAll(claimsMap))
                .build();

        return this.tokenEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

    }

}