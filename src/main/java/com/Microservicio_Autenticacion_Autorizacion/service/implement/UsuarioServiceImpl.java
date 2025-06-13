package com.Microservicio_Autenticacion_Autorizacion.service.implement;

import com.Microservicio_Autenticacion_Autorizacion.persistence.Repository.RolRepository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.Repository.UsuarioRepository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Rol;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.persistence.mapper.UsuarioMapper;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioUpdateDto;
import com.Microservicio_Autenticacion_Autorizacion.presentation.exception.BadRequestException;
import com.Microservicio_Autenticacion_Autorizacion.service.port.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;

    private final UsuarioMapper usuarioMapper;

    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, UsuarioMapper usuarioMapper, RolRepository rolRepository) {
        this.usuarioRepo = usuarioRepo;
        this.usuarioMapper = usuarioMapper;
        this.rolRepository = rolRepository;
    }

    @Override
    public List<UsuarioResponseDTO> findAllUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepo.findAll();
        return usuarioMapper.toDtoList(usuarios);
    }

    @Override
    public UsuarioResponseDTO findUsuarioById(int id) {
        return usuarioRepo.findById(id)
              .map(usuarioMapper::toDto)
              .orElse(null);
    }

    @Override
    public UsuarioResponseDTO saveUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {

        if (usuarioRegistroDTO.rolId() == 2 && (usuarioRegistroDTO.numeroColegiado() == null || usuarioRegistroDTO.numeroColegiado().isBlank())) {
            throw new BadRequestException("El número de colegiado no puede ser nulo para el rol de profesional");
        }

        Rol rol = rolRepository.findById(usuarioRegistroDTO.rolId())
              .orElseThrow(() -> new BadRequestException("Rol no encontrado con ID: " + usuarioRegistroDTO.rolId()));

        Usuario usuario = usuarioMapper.toEntity(usuarioRegistroDTO);
        usuario.setRol(rol);
        Usuario savedUsuario = usuarioRepo.save(usuario);
        return usuarioMapper.toDto(savedUsuario);
    }

    @Override
    public UsuarioResponseDTO updateUsuario(int id, UsuarioUpdateDto usuarioUpdateDto) {
        Usuario existingUsuario = usuarioRepo.findById(id).orElse(null);
        if (existingUsuario == null) {
            throw new BadRequestException("Usuario no encontrado con ID: " + id);
        }
        Rol rol = rolRepository.findById(usuarioUpdateDto.rolId())
              .orElseThrow(() -> new BadRequestException("Rol no encontrado con ID: " + usuarioUpdateDto.rolId()));
        usuarioMapper.updateEntityFromDto(usuarioUpdateDto, existingUsuario);
        existingUsuario.setRol(rol);
        Usuario updatedUsuario = usuarioRepo.save(existingUsuario);
        return usuarioMapper.toDto(updatedUsuario);
    }

    @Override
    public void deleteUsuario(int id) {
        Usuario usuario = usuarioRepo.findById(id)
              .orElseThrow(() -> new BadRequestException("Usuario no encontrado con ID: " + id));
        usuarioRepo.delete(usuario);
    }
}
