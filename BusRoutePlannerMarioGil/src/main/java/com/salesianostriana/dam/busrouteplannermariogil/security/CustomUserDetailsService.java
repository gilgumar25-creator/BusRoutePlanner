package com.salesianostriana.dam.busrouteplannermariogil.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Usuario;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByNombre(username)
                .orElseThrow(() ->
                    new UsernameNotFoundException("Usuario no encontrado: " + username));

        // El rol almacenado es "ADMIN" u "OPERADOR", Spring Security espera "ROLE_ADMIN" etc.
        String role = "ROLE_" + usuario.getRol().name();

        // Si es ADMIN, también tiene permisos de OPERADOR
        String[] roles = usuario.getRol().name().equals("ADMIN")
                ? new String[]{"ROLE_ADMIN", "ROLE_OPERADOR"}
                : new String[]{"ROLE_OPERADOR"};

        return User.builder()
                .username(usuario.getNombre())
                .password(usuario.getPassword())   // ya incluye el prefijo {noop}
                .authorities(roles)
                .build();
    }

}
