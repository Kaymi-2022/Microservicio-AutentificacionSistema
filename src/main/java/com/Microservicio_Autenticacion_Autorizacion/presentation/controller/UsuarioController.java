package com.Microservicio_Autenticacion_Autorizacion.presentation.controller;

import com.Microservicio_Autenticacion_Autorizacion.persistence.repository.UsuarioRepository;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;
import com.Microservicio_Autenticacion_Autorizacion.service.port.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public ResponseEntity<Page<UsuarioResponseDTO>> listaUsuario(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "5")int elements,
                                                                 @RequestParam(defaultValue = "idUsuario") String sortby,
                                                                 @RequestParam(defaultValue = "ASC") String direction) {
        Page<UsuarioResponseDTO> usuarios = usuarioService.findAllUsuarios(page,elements,sortby,direction);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioxId(@PathVariable("id") int id) {
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

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(@PathVariable("idUsuario") int idUsuario, @RequestBody UsuarioUpdateDTO usuarioUpdateDto) {
        UsuarioResponseDTO updatedUsuario = usuarioService.updateUsuario(idUsuario, usuarioUpdateDto);
        return updatedUsuario != null
                ? ResponseEntity.status(HttpStatus.OK).body(updatedUsuario)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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

    @GetMapping("/activos")
    public ResponseEntity<List<UsuarioResponseDTO>> findTop3ByIdUser(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findTop3User());
    }

}
