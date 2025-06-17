package com.Microservicio_Autenticacion_Autorizacion.presentation.controller;

import com.Microservicio_Autenticacion_Autorizacion.configuration.JwtUtil;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.LoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        System.out.println("Authentication successful for user: " + authentication.isAuthenticated()
                + "\nand Principal: " + authentication.getPrincipal());
                
        String jwt = this.jwtUtil.generateToken(loginRequestDTO.username());
        boolean isValid = this.jwtUtil.isValidToken(jwt);
        return  ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body("Token is valid: " + isValid + "\n" +
                "JWT: " + jwt + "\n" +
                "Username from JWT: " + this.jwtUtil.Username(jwt));

    }
}
