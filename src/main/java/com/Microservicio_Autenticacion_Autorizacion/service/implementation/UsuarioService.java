package com.Microservicio_Autenticacion_Autorizacion.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.service.interfaces.UsuarioDtoRepository;

import jakarta.validation.ValidationException;

@Service
public class UsuarioService {

    @Autowired
    UsuarioDtoRepository usuarioDtoRepository;

    private final Validator validator;

    public UsuarioService(Validator validator) {
        this.validator = validator;
    }

    public List<UsuarioResponseDTO> getAllUsuarios() {
        return usuarioDtoRepository.findAllUsuarios();
    }

    public UsuarioResponseDTO getUsuarioById(int id) {
        return usuarioDtoRepository.findUsuarioById(id);
    }

    public UsuarioResponseDTO createUsuario(UsuarioRegistroDTO dto) {
        Errors errors = new BeanPropertyBindingResult(dto, "usuarioRegistroDTO");
        validator.validate(dto, errors);
        
        if (errors.hasErrors()) {
            throw new ValidationException("Error de validación: " + errors.getAllErrors());
        }
        return usuarioDtoRepository.saveUsuario(dto);
    }

    public UsuarioResponseDTO updateUsuario(int id, UsuarioRegistroDTO usuarioRegistroDTO) {
        // verificar que tenga numero de colegiatura para ser usuario medico
        if (usuarioRegistroDTO.rolId() == 2
                && (usuarioRegistroDTO.numeroColegiado() == null || usuarioRegistroDTO.numeroColegiado().isEmpty())) {
            throw new IllegalArgumentException("El número de colegiatura es obligatorio para usuarios médicos.");
        }
        return usuarioDtoRepository.updateUsuario(id, usuarioRegistroDTO);
    }

    public void deleteUsuario(int id) {
        usuarioDtoRepository.deleteUsuario(id);
    }

}
