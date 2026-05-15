package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.service.RouteService;

@Controller
public class RouteController {
	
private final RouteService service;

public RouteController(RouteService servicio) {
	this.service = servicio;
}


	@GetMapping("/Ruta")
	public String showRutas(Model model) {
		Route C2 = new Route(
				"Avenida del Aljarafe,Palomares del Río",
				"Parque de las Delicias",
				2
				
				
				);
		
		model.addAttribute("route",C2);
		model.addAttribute("mensajeBienvenida","Muy buenas, te presentamos la mejor página web de rutas de autobuses del mundo, próximamente la Junta de Andalucía comprará esta página jeje");
		
		return "rutas";
	}
		
	@GetMapping("/listaRutas")
	public String showListaRutas(Model model) {
		
		model.addAttribute("routesList",service.getLista());
		
		return "listaRutas";
	
	}
	
	@GetMapping("/newRuta")
	public String muestraFormulario(Model model) {
	model.addAttribute("route", new Route());
	return "registrousuario";
	}
	
	@PostMapping("/newRuta/submit")
	public String processForm(@ModelAttribute("route")Route ruta) {
		service.addRuta(ruta);
		
		return "redirect:/index";
		
		
	}
	
	
	
	
	
	
	@GetMapping("/Registro")
	public String register(Model model) {
		return "registrousuario";
	}

	
	
		

}
