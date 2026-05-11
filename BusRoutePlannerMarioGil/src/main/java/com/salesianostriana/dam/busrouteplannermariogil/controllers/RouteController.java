package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.service.RouteService;

import lombok.RequiredArgsConstructor;

@Controller @RequiredArgsConstructor
public class RouteController {
	
private final RouteService service;
	
	@GetMapping("/Ruta")
	public String showRutas(Model model) {
		Route C2 = new Route(
				19754,
				"Avenida del Aljarafe,Palomares del Río",
				"Parque de las Delicias",
				2
				
				
				);
		
		model.addAttribute("ruta",C2);
		model.addAttribute("mensajeBienvenida","Muy buenas, te presentamos la mejor página web de rutas de autobuses del mundo, próximamente la Junta de Andalucía comprará esta página jeje");
		
		return "rutas";
	}
		
	@GetMapping("/listaRutas")
	public String showLista(Model model) {
		
		model.addAttribute("routesList",service.getLista());
		return "listaRutas";
	
	}
	
	
	@GetMapping("/Registro")
	public String register(Model model) {
		return "registrousuario";
	}
		

}
