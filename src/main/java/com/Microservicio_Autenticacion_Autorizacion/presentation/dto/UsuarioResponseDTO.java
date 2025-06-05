package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

public record UsuarioResponseDTO(
    Integer usuarioId,
    String username,
    String name,
    String lastname,
    String email,
    String telefono,
    String numeroColegiado,
    String especialidad,
    Boolean activo,
    RolResponseDTO rol
) {}