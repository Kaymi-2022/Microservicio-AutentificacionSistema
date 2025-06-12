package com.Microservicio_Autenticacion_Autorizacion.presentation.controller;


import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioRegistroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Microservicio_Autenticacion_Autorizacion.presentation.dto.UsuarioResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;




    @GetMapping("/")
    public ResponseEntity<List<UsuarioResponseDTO>> getAllUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioServiceImpl.getAllUsuarios();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuarioById(int id) {
        UsuarioResponseDTO usuario = usuarioServiceImpl.getUsuarioById(id);
        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/crear")
    public ResponseEntity<UsuarioResponseDTO> createUsuario(UsuarioRegistroDTO usuarioRegistroDTO) {
        try {
            UsuarioResponseDTO createdUsuario = usuarioServiceImpl.createUsuario(usuarioRegistroDTO);
            return new ResponseEntity<>(createdUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUsuario(int id, UsuarioRegistroDTO usuarioRegistroDTO) {
        try {
            UsuarioResponseDTO updatedUsuario = usuarioServiceImpl.updateUsuario(id, usuarioRegistroDTO);
            return new ResponseEntity<>(updatedUsuario, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("id") int id) {
        try {
            usuarioServiceImpl.deleteUsuario(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
