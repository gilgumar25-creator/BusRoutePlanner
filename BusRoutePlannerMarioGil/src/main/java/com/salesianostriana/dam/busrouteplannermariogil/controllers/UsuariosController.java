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
	public String nuevoUser() {
	    // Aquí solo devuelves el nombre de tu archivo HTML del formulario
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

	    Usuario usuarioAGuardar;
	    Rol rol = Rol.valueOf(rolStr); 

	    // 1. ¿ES UNA EDICIÓN? 
	    if (id != null) {
	        // Buscamos el usuario real que ya está en la base de datos con su herencia intacta
	        usuarioAGuardar = servicio.findById(id).orElse(null);
	        
	        // Modificamos los datos específicos según el tipo real del objeto
	        if (usuarioAGuardar instanceof Admin admin) {
	            if (fecha != null && !fecha.isEmpty()) {
	                admin.setFechaNombramiento(LocalDate.parse(fecha));
	            }
	        } else if (usuarioAGuardar instanceof Operador operador) {
	            if (activo != null) {
	                operador.setActivo(activo);
	            }
	        }
	    } 
	    // 2. ¿ES UN USUARIO NUEVO?
	    else {
	        if (rol == Rol.ADMIN) {
	            Admin admin = new Admin();
	            admin.setFechaNombramiento(LocalDate.now()); 
	            usuarioAGuardar = admin;
	        } else {
	            Operador operador = new Operador();
	            operador.setActivo(true); 
	            usuarioAGuardar = operador;
	        }
	    }

	    // 3. Modificamos los datos comunes para ambos casos (Nuevo y Editado)
	    usuarioAGuardar.setNombre(nombre);
	    
	    // Evitamos volver a ponerle {noop} si la contraseña ya lo traía de antes
	    if (password.startsWith("{noop}")) {
	        usuarioAGuardar.setPassword(password);
	    } else {
	        usuarioAGuardar.setPassword("{noop}" + password);
	    }
	    
	    usuarioAGuardar.setRol(rol);
	    
	    // 4. Guardamos. Al venir de la base de datos (en caso de edición), Hibernate hará un UPDATE automático
	    servicio.save(usuarioAGuardar);
	    
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
