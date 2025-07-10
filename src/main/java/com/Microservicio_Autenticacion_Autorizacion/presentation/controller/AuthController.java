package com.Microservicio_Autenticacion_Autorizacion.presentation.controller;

import com.Microservicio_Autenticacion_Autorizacion.configuration.JwtUtil;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.AuthResponse;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.LoginRequestDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.service.port.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            // 1. Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.username(),
                        loginRequestDTO.password()
                  )
            );


            // 2. Generar token JWT (sin incluir authorities)
            String jwt = jwtUtil.generateToken(loginRequestDTO.username(), authentication);

            // 3. Crear respuesta simplificada
            AuthResponse response = new AuthResponse(jwt, loginRequestDTO.username(),
                  authentication.getAuthorities().toString());

            return ResponseEntity.ok()
                  .header(HttpHeaders.AUTHORIZATION, jwt)
                  .body(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                  .body(new AuthResponse(null, "Autenticación fallida", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.saveUsuario(usuarioRegistroDTO);
        if (usuarioResponseDTO == null) {
            return ResponseEntity.status(400).body("Error al registrar el usuario");
        }
        // 1. Autenticar al usuario
        Authentication authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                    usuarioRegistroDTO.username(),
                    usuarioRegistroDTO.password()
              )
        );
        String jwt = this.jwtUtil.generateToken(usuarioResponseDTO.username(), authentication);
        AuthResponse authResponse = new AuthResponse(jwt, usuarioResponseDTO.username(), authentication.getAuthorities().toString());
        return authResponse != null
              ? ResponseEntity.status(HttpStatus.CREATED).body(authResponse.toString())
              : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar el usuario");
    }
}
