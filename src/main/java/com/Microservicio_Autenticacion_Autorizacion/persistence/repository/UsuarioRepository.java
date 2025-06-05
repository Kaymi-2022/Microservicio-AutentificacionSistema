package com.Microservicio_Autenticacion_Autorizacion.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Microservicio_Autenticacion_Autorizacion.persistence.crud.UsuarioCrudRepository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.model.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.service.interfaces.UsuarioDtoRepository;
import com.Microservicio_Autenticacion_Autorizacion.util.mapper.UsuarioMapper;

import java.util.List;

@Repository
public class UsuarioRepository implements UsuarioDtoRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @Override
    public UsuarioResponseDTO findUsuarioById(int id) {
        Usuario usuario = usuarioCrudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario not found with id: " + id));
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRegistroDTO);
        Usuario savedUsuario = usuarioCrudRepository.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    @Override
    public UsuarioResponseDTO updateUsuario(int id, UsuarioRegistroDTO usuarioRegistroDTO) {

        return null; // or throw an exception
    }

    @Override
    public void deleteUsuario(int id) {
        UsuarioResponseDTO usuario = findUsuarioById(id);
        if (usuario != null) {
            usuarioCrudRepository.deleteById(id);
        }
    }

    @Override
    public List<UsuarioResponseDTO> findAllUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioCrudRepository.findAll();
        return usuarioMapper.toDtoList(usuarios);
    }
}
