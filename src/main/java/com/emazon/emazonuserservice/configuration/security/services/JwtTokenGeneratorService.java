package com.emazon.emazonuserservice.configuration.security.services;

import com.emazon.emazonuserservice.configuration.security.config.CustomUserDetails;
import com.emazon.emazonuserservice.configuration.security.constants.ClaimTokenConstants;
import com.emazon.emazonuserservice.configuration.security.constants.ErrorMessageConstants;
import com.emazon.emazonuserservice.configuration.security.constants.SecurityConstants;
import com.emazon.emazonuserservice.domain.exception.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;



public class JwtTokenGeneratorService {

    @Value("${jwt.time.expiration}")
    private Integer timeExpiration;


    private final JwtEncoder tokenEncoder;
    public JwtTokenGeneratorService(JwtEncoder tokenEncoder) {
        this.tokenEncoder = tokenEncoder;
    }

    public String generateJwtToken(Authentication authentication){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Instant now = Instant.now();

        String role =  authentication
                .getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElseThrow(() -> new RoleNotFoundException(ErrorMessageConstants.ROLE_NOT_FOUND));


        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put(ClaimTokenConstants.CLAIM_NAME_FIELD_USERID, userDetails.getId());
        claimsMap.put(ClaimTokenConstants.CLAIM_NAME_FIELD_NAME, userDetails.getName());
        claimsMap.put(ClaimTokenConstants.CLAIM_NAME_FIELD_ROLE, role);

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