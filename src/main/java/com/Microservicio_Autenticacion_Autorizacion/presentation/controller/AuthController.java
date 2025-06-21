package com.Microservicio_Autenticacion_Autorizacion.presentation.controller;

import com.Microservicio_Autenticacion_Autorizacion.configuration.JwtUtil;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.LoginRequestDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.service.port.UsuarioService;
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

    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
              new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }


        String jwt = this.jwtUtil.generateToken(loginRequestDTO.username());
        boolean isValid = this.jwtUtil.isValidToken(jwt);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body("Token is valid: " + isValid + "\n" +
              "JWT: " + jwt + "\n" +
              "Username from JWT: " + this.jwtUtil.Username(jwt));

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.saveUsuario(usuarioRegistroDTO);
        if (usuarioResponseDTO == null) {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
        String jwt = this.jwtUtil.generateToken(usuarioResponseDTO.username());
        return ResponseEntity.status(201).body("Usuario registrado correctamente:\n" + usuarioResponseDTO.username()
              + "\nJWT: " + jwt + "\n" +
              "Username from JWT: " + this.jwtUtil.Username(jwt));
    }

}
