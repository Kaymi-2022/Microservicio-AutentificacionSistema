package com.Microservicio_Autenticacion_Autorizacion.service.port;



import java.util.List;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioUpdateDTO;
import org.springframework.data.domain.Page;

public interface UsuarioService {

    //List<UsuarioResponseDTO> findAllUsuarios();

    Page<UsuarioResponseDTO> findAllUsuarios(int page, int elements,
                                             String sortby,
                                             String direction);

    UsuarioResponseDTO findUsuarioById(int id);
    UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO usuarioRegistroDTO);
    UsuarioResponseDTO updateUsuario(int id, UsuarioUpdateDTO usuarioUpdateDto);
    void deleteUsuario(int id);

    List<UsuarioResponseDTO> findTop3User();
}
