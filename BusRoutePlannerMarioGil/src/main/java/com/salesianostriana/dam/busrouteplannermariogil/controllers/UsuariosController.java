package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Admin;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Operador;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Rol;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Usuario;
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
	public String nuevoUsuario() {
	    return "admin/formUsuario"; 
	}

	/*@PostMapping("/guardarUsuario")
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

    }*/
	
	@PostMapping("/guardarUsuario")
	public String guardarUsuario(
	    @RequestParam(value = "id", required = false) Integer id, 
	    @RequestParam("nombre") String nombre,
	    @RequestParam("password") String password,
	    @RequestParam("rol") String rolStr,
	    @RequestParam(value = "activo", required = false) Boolean activo, // Captura el estado del operador
	    @RequestParam(value = "fechaNombramiento", required = false) String fecha) { // Captura la fecha del admin

	    Usuario usuario;
	    Rol rol = Rol.valueOf(rolStr); 

	    if (id != null) {
	        usuario = servicio.findById(id).orElse(null);
	        if (usuario instanceof Admin admin) {
	            if (fecha != null && !fecha.isEmpty()) {
	                admin.setFechaNombramiento(LocalDate.parse(fecha));
	            }
	        } else if (usuario instanceof Operador operador) {
	            if (activo != null) {
	                operador.setActivo(activo);
	            }
	        }
	    } 
	    else {
	        if (rol == Rol.ADMIN) {
	            Admin admin = new Admin();
	            admin.setFechaNombramiento(LocalDate.now()); 
	            usuario = admin;
	        } else {
	            Operador operador = new Operador();
	            operador.setActivo(true); 
	            usuario = operador;
	        }
	    }

	    usuario.setNombre(nombre);
	    
	    if (password.startsWith("{noop}")) {
	        usuario.setPassword(password);
	    } else {
	        usuario.setPassword("{noop}" + password);
	    }
	    
	    usuario.setRol(rol);
	    
	    servicio.save(usuario);
	    
	    return "redirect:/admin/listaUsuarios";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") Integer id, Model model) {
	   
	    Usuario usuario = servicio.findById(id).orElse(null); 
	    
	    if (usuario == null) {
	        return "redirect:/admin/listaUsuarios?error=notfound";
	    }

	   
	    model.addAttribute("usuario", usuario);

	    
	    if (usuario.getRol() == Rol.ADMIN) {
	      
	        return "admin/formAdmin"; 
	    } else {
	        return "admin/formOperador";
	    }
	    
	}
	
	@GetMapping("/borrarUsuario/{id}")
	public String borrarUsuario(@PathVariable("id") Integer id, Model model) {
		servicio.deleteById(id);
		return "redirect:/admin/listaUsuarios";
		}
	
	
	
	}
