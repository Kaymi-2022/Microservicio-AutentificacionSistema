package com.Microservicio_Autenticacion_Autorizacion.presentation.dto;

import java.util.Date;

public record UsuarioUpdateDto(
      Integer idUsuario,
      String username,
      String password,
      String name,
      String lastname,
      String email,
      String telefono,
      String numeroColegiado,
      String especialidad,
      String fotoPerfil,
      Boolean activo,
      Date fechaCreacion,
      Integer rolId  // Cambiado a Integer para manejar solo el ID del rol
) {}
