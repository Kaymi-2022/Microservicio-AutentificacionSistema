package com.Microservicio_Autenticacion_Autorizacion.persistence.repository;

import com.Microservicio_Autenticacion_Autorizacion.persistence.crud.UsuarioCrud;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.persistence.mapper.UsuarioMapper;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioUpdateDto;
import com.Microservicio_Autenticacion_Autorizacion.service.port.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsuarioRepository  implements UsuarioService {

    private final UsuarioCrud usuarioCrud;

    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioResponseDTO> findAllUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioCrud.findAll();
        return usuarioMapper.toDtoList(usuarios);
    }

    @Override
    public UsuarioResponseDTO findUsuarioById(int id) {
        return usuarioCrud.findById(id)
                .map(usuarioMapper::toDto)
                .orElse(null);
    }

    @Override
    public UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRegistroDTO);
        Usuario savedUsuario = usuarioCrud.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    @Override
    public UsuarioResponseDTO updateUsuario(int id, UsuarioUpdateDto usuarioUpdateDto) {
        Usuario existingUsuario = usuarioCrud.findById(id).orElse(null);
        if(existingUsuario != null) {
            usuarioMapper.updateEntityFromDto(usuarioUpdateDto, existingUsuario);
            Usuario updatedUsuario = usuarioCrud.save(existingUsuario);
            return usuarioMapper.toDto(updatedUsuario);
        }
        return null;
    }

    @Override
    public void deleteUsuario(int id) {
        //actualiza el esatado de usuario a inactivo
        Usuario existingUsuario = usuarioCrud.findById(id).orElse(null);
        if (existingUsuario != null) {
            existingUsuario.setActivo(false);
            usuarioCrud.save(existingUsuario);
        }
    }
}
