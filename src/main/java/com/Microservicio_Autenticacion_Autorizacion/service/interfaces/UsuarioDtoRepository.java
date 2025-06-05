package com.Microservicio_Autenticacion_Autorizacion.service.interfaces;



import java.util.List;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;

public interface UsuarioDtoRepository {

    List<UsuarioResponseDTO> findAllUsuarios();
    UsuarioResponseDTO findUsuarioById(int id);
    UsuarioResponseDTO findUsuarioByEmail(String email);
    UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO usuarioRegistroDTO);
    UsuarioResponseDTO updateUsuario(int id, UsuarioRegistroDTO usuarioRegistroDTO);
    void deleteUsuario(int id);
}
