package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class UsuarioService {
	
	private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    public UsuarioService(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }
    
    public List<String> findAll() {
        return List.of("admin", "operador");
    }

    // 2. CREAR / GUARDAR UN USUARIO
    public void save(String username, String password, String rol) {
        UserDetails nuevoUsuario = User.builder()
                .username(username)
                .password("{noop}" + password)
                .roles(rol)
                .build();
        
        inMemoryUserDetailsManager.createUser(nuevoUsuario);
    }

}
