package com.Microservicio_Autenticacion_Autorizacion.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.service.interfaces.UsuarioDtoRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDtoRepository usuarioDtoRepository;

    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioDtoRepository.findAllUsuarios();
    }


}
