package br.com.c137.project.sellmotors.authcommand.security.services;

import br.com.c137.project.sellmotors.authcommand.multitenancy.mastertenant.dtos.gets.UserTokenGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    public String gerarToken(UserTokenGetDTO userTokenGetDTO) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plus(15, ChronoUnit.MINUTES))
                .subject(userTokenGetDTO.id().toString())
                .claim("roles", userTokenGetDTO.userRole())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    public String gerarRefreshToken(UserTokenGetDTO userTokenGetDTO) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-service")
                .issuedAt(now)
                .expiresAt(now.plus(15, ChronoUnit.DAYS))
                .subject(userTokenGetDTO.id().toString())
                .claim("roles", userTokenGetDTO.userRole())
                .build();

        JwsHeader jwsHeader = JwsHeader.with(SignatureAlgorithm.RS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
