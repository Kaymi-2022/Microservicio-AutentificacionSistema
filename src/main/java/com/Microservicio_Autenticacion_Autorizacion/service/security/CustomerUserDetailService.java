package com.Microservicio_Autenticacion_Autorizacion.service.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Microservicio_Autenticacion_Autorizacion.persistence.Repository.UsuarioRepository;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import com.Microservicio_Autenticacion_Autorizacion.presentation.exception.BadRequestException;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public CustomerUserDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new BadRequestException("Usuario no encontrado: " + username);
        }

        // Loggear información importante
        System.out.println("=== DEBUG: Comparación de contraseñas ===");
        System.out.println("Usuario: " + username);
        System.out.println("Contraseña almacenada (hash): " + usuario.getPassword());
        System.out.println("¿Es un hash BCrypt válido?: " + 
            (usuario.getPassword().startsWith("$2a$") ? "Sí" : "No"));

        
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .disabled(!usuario.getActivo())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .roles(usuario.getRol().getNombreRol().toUpperCase())
                .build();
    }

}
