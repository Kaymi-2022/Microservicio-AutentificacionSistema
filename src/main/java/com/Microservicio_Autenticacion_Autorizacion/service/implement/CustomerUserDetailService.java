package com.Microservicio_Autenticacion_Autorizacion.service.implement;

import com.Microservicio_Autenticacion_Autorizacion.persistence.repository.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.Microservicio_Autenticacion_Autorizacion.persistence.entity.Usuario;

import java.util.ArrayList;
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

        System.out.println("Autorities: " + getAuthorities(usuario).stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", ")));

        return User.builder()
              .username(usuario.getUsername())
              .password(usuario.getPassword())
              .disabled(!usuario.getActivo())
              .accountExpired(false)
              .accountLocked(false)
              .credentialsExpired(false)
              .authorities(getAuthorities(usuario))
              .build();

    }

    public String[] getRoles (Usuario usuario) {
        if (usuario.getRol() == null || usuario.getRol().getNombreRol() == null) {
            return new String[0];
        }
        return usuario.getRol().getNombreRol().split("\\s*,\\s*");
    }

    public List<GrantedAuthority> getAuthorities(Usuario usuario) {
        List<String> permisos = usuarioRepository.findPermisosCodigoByUsuarioId(usuario.getIdUsuario());
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String permiso : permisos) {
            authorities.add(new SimpleGrantedAuthority(permiso));
        }
        for (String rol : getRoles(usuario)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));
        }
        return authorities;
    }

}
