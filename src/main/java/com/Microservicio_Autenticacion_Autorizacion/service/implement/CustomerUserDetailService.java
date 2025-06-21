package com.Microservicio_Autenticacion_Autorizacion.service.implement;

import com.Microservicio_Autenticacion_Autorizacion.persistence.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;
import java.util.List;
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
