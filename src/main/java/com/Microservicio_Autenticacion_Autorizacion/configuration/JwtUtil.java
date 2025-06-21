package com.Microservicio_Autenticacion_Autorizacion.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtil {

    private static final String SECRET_KEY = "Pl4tz1_k4ym1lygjjj5567756777-988";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(String username, Authentication authentication) {
        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }

        return JWT.create()
              .withSubject(username)
              .withIssuer("kaymilyg")
              .withIssuedAt(new Date())
              .withClaim("authorities", authorities)
              .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(50))) // Token valid for 50 minutes
              .sign(ALGORITHM);
    }

    public boolean isValidToken(String jwt) {

        try {
            JWT.require(ALGORITHM)
                  .build()
                  .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Invalid JWT token: " + e.getMessage());
        } catch (Exception e) {
            log.error("Error verifying JWT token: {}", e.getMessage());
            return false;
        }
    }

    public String Username(String jwt) {
        return JWT.require(ALGORITHM)
              .build()
              .verify(jwt)
              .getSubject();
    }
}
