package com.Microservicio_Autenticacion_Autorizacion.service.implementation;


import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
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

    public UsuarioResponseDTO getUsuarioById(int id) {
        return usuarioDtoRepository.findUsuarioById(id);
    }

    public UsuarioResponseDTO createUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        // verificar que tenga numero de colegiatura para ser usuario medico
        if(usuarioRegistroDTO.rolId() == 2 && (usuarioRegistroDTO.numeroColegiado() == null || usuarioRegistroDTO.numeroColegiado().isEmpty())) {
            throw new IllegalArgumentException("El número de colegiatura es obligatorio para usuarios médicos.");
        }
        return usuarioDtoRepository.saveUsuario(usuarioRegistroDTO);
    }

    public UsuarioResponseDTO updateUsuario(int id, UsuarioRegistroDTO usuarioRegistroDTO) {
        // verificar que tenga numero de colegiatura para ser usuario medico
        if(usuarioRegistroDTO.rolId() == 2 && (usuarioRegistroDTO.numeroColegiado() == null || usuarioRegistroDTO.numeroColegiado().isEmpty())) {
            throw new IllegalArgumentException("El número de colegiatura es obligatorio para usuarios médicos.");
        }
        return usuarioDtoRepository.updateUsuario(id, usuarioRegistroDTO);
    }

    public void deleteUsuario(int id) {
        usuarioDtoRepository.deleteUsuario(id);
    }


}
