package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

public record PermisoResponseDTO(
    Integer permisoId,
    String codigo,
    String descripcion
) {}