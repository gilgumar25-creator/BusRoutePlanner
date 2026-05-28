package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.busrouteplannermariogil.service.UsuarioService;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuariosController {
	
	private final UsuarioService service;

	public UsuariosController(UsuarioService usuarioService) {
        this.service = usuarioService;
    }
        
        @GetMapping
        public String listarUsuarios(Model model) {
            
            return "admin/lista-usuarios"; 
        }
        
        
	
	
    }

    
    
