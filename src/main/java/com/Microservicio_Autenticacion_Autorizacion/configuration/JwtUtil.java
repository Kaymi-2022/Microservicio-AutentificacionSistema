package com.Microservicio_Autenticacion_Autorizacion.configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "kaymi0302";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public String generateToken(String username) {
       return JWT.create()
                .withSubject(username)
                .withIssuer("kym0302")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1))) // Token valid for 1 day
                .sign(ALGORITHM);
    }
}
