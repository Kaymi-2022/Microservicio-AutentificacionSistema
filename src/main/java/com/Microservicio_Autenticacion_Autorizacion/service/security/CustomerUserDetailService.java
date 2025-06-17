package com.Microservicio_Autenticacion_Autorizacion.service.security;

import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Rol;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.RolPermiso;
import com.Microservicio_Autenticacion_Autorizacion.persistence.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.presentation.exception.BadRequestException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomerUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        List<String> permisos = usuarioRepository.findPermisosCodigoByUsuarioId(usuario.getIdUsuario());

        // Loggear información importante
        System.out.println("=== DEBUG: Comparación de contraseñas ===");
        System.out.println("Usuario: " + username);
        System.out.println("Contraseña almacenada (hash): " + usuario.getPassword());
        System.out.println("¿Es un hash BCrypt válido?: " + 
            (usuario.getPassword().startsWith("$2a$") ? "Sí" : "No"));
        System.out.println("Permisos del usuario: " + permisos);

        
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .disabled(!usuario.getActivo())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .roles(usuario.getRol().getNombreRol().toUpperCase())
                .authorities(permisos.stream()
                        .map(permiso -> new SimpleGrantedAuthority(permiso.toUpperCase()))
                        .collect(Collectors.toList()))
                .build();

    }

}
