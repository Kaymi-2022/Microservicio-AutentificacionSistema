package com.Microservicio_Autenticacion_Autorizacion.service.port;



import java.util.List;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioUpdateDTO;

public interface UsuarioService {

    List<UsuarioResponseDTO> findAllUsuarios();
    UsuarioResponseDTO findUsuarioById(int id);
    UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO usuarioRegistroDTO);
    UsuarioResponseDTO updateUsuario(int id, UsuarioUpdateDTO usuarioUpdateDto);
    void deleteUsuario(int id);
}
