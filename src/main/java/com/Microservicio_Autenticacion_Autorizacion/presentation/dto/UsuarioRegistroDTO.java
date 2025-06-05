package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import jakarta.validation.constraints.*;

public record UsuarioRegistroDTO (
    @NotBlank String username,
    @NotBlank @Size(min = 8) String password,
    @NotBlank String name,
    @NotBlank String lastname,
    @NotBlank @Email String email,
    String telefono,
    String numeroColegiado,
    String especialidad,
    String fotoPerfil,
    @NotNull Boolean activo,
    @NotNull Integer rolId
){}
