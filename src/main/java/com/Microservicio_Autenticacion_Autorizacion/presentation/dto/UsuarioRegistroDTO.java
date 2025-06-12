package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRegistroDTO (
    @NotBlank(message = "El nombre de usuario es obligatorio") 
    String username,

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    String password,

    @NotBlank(message = "El nombre es obligatorio") 
    String name,

    @NotBlank(message = "El apellido es obligatorio") 
    String lastname,

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    String email,

    String telefono,  // Opcional

    @NotBlank(message = "El número de colegiatura es obligatorio para médicos", 
              groups = MedicoGroup.class)
    String numeroColegiado,

    String especialidad,

    String fotoPerfil,

    @NotNull(message = "El rol es obligatorio") 
    Integer rolId
) {
    // Grupo para validación de médicos
    public interface MedicoGroup {}
}
