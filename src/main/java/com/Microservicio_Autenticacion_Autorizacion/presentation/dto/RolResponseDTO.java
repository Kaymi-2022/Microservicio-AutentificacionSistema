package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import java.util.List;

public record RolResponseDTO(
    Integer rolId,
    String nombreRol,
    List<PermisoResponseDTO> permisos
) {}