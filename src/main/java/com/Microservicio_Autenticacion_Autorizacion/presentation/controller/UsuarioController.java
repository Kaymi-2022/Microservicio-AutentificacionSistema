package com.Microservicio_Autenticacion_Autorizacion.presentation.controller;

import com.Microservicio_Autenticacion_Autorizacion.persistence.Repository.UsuarioRepository;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioUpdateDto;
import com.Microservicio_Autenticacion_Autorizacion.service.port.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioResponseDTO>> listaUsuario() {
        List<UsuarioResponseDTO> usuarios = usuarioService.findAllUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioxId(@PathVariable("id")int id) {
        UsuarioResponseDTO usuario = usuarioService.findUsuarioById(id);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO usuarioRegistroDTO) {
        UsuarioResponseDTO createdUsuario = usuarioService.saveUsuario(usuarioRegistroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable("id") int id, @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
        try {
            UsuarioResponseDTO updatedUsuario = usuarioService.updateUsuario(id, usuarioUpdateDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUsuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable("id") int id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteAllUsuarios(@PathVariable("id") int id) {
        try {

            if (!usuarioRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario no encontrado");
            }
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
