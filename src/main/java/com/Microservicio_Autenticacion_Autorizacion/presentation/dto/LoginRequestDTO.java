package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank String username,
        @NotBlank String password) {
}
