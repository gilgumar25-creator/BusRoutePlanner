package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Admin;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Operador;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Rol;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Usuario;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.UsuarioRepository;
import com.salesianostriana.dam.busrouteplannermariogil.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class UsuariosController {

	private final UsuarioService servicio;

	public UsuariosController(UsuarioService servicio) {
		this.servicio = servicio;
	}

	@GetMapping("/listaUsuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("userList", servicio.findAll());
		return "admin/listaUsuarios";

	}
	@GetMapping("/nuevoUsuario")
	public String nuevoUser() {
	    // Aquí solo devuelves el nombre de tu archivo HTML del formulario
	    return "admin/formUsuario"; 
	}

	@PostMapping("/guardarUsuario")
	public String guardarUser(
	@RequestParam("nombre") String nombre,
    @RequestParam("password") String password,
    @RequestParam("rol") String rolStr) {

		Usuario nuevoUsuario;
		// Convierte el String "ADMIN" u "OPERADOR" al Enum
		Rol rol = Rol.valueOf(rolStr); 
		

		if (rol == Rol.ADMIN) {
		    Admin admin = new Admin();
		    admin.setFechaNombramiento(LocalDate.now()); 
		    nuevoUsuario = admin;
		} else {
		    Operador operador = new Operador();
		    operador.setActivo(true); 
		    nuevoUsuario = operador;
		}
		nuevoUsuario.setNombre(nombre);
		nuevoUsuario.setPassword("{noop}" + password); 
		nuevoUsuario.setRol(rol);
		servicio.save(nuevoUsuario);
		
		return "redirect:/admin/listaUsuarios";
			
	
	
        
	
	
    }
	
}
