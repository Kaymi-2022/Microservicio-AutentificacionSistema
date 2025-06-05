package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RolRequestDTO(
    @NotBlank String nombreRol,
    @NotNull Integer nivelPermisos,
    List<Integer> permisosIds  // IDs de permisos asociados
) {}