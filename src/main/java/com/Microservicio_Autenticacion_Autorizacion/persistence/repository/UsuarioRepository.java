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
import java.util.Optional;

@Repository
public class UsuarioRepository implements UsuarioDtoRepository {

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @Override
    public UsuarioResponseDTO findUsuarioById(int id) {
        Usuario usuario = usuarioCrudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el id: " + id));
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
        if(!usuarioCrudRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioRegistroDTO);
        usuario.setIdUsuario(id); // Asegurarse de que el ID se establezca correctamente
        Usuario updatedUsuario = usuarioCrudRepository.save(usuario);
        return usuarioMapper.toDto(updatedUsuario);
    }

    @Override
    public void deleteUsuario(int id) {
        Optional<Usuario> usuarios = usuarioCrudRepository.findById(id);
        if (usuarios.isPresent()) {
            // Verificar si el usuario está activo
            if (!usuarios.get().getActivo()) {
                throw new RuntimeException("El usuario ya está inactivo.");
            }
            // Desactivar el usuario en lugar de eliminarlo
            Usuario usuario = usuarios.get();
            usuario.setActivo(false);
            usuarioCrudRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuario no encontrado con el id: " + id);
        }

    }

    @Override
    public List<UsuarioResponseDTO> findAllUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioCrudRepository.findAll();
        return usuarioMapper.toDtoList(usuarios);
    }

}
