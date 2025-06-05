package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import java.util.List;

public record UsuarioAuthResponseDTO(
    String token,
    Integer usuarioId,
    String username,
    String name,
    String lastname,
    String email,
    String rol,
    List<String> permisos
) {}