package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.service.BusService;



@Controller
public class BusController {
	
private final BusService service;

public BusController(BusService servicio) {
	this.service = servicio;
}


	@GetMapping("/Bus")
	public String showBuses(Model model) {
		Bus C2 = new Bus(
				);
		
		model.addAttribute("bus",C2);
		model.addAttribute("mensajeBienvenida","Muy buenas, te presentamos la mejor página web de rutas de autobuses del mundo, próximamente la Junta de Andalucía comprará esta página jeje");
		
		return "buses";
	}
		
	@GetMapping("/listaBuses")
	public String showListaBuses(Model model) {
		
		model.addAttribute("busList",service.getLista());
		
		return "listaBuses";
	
	}
	
	@GetMapping("/newBus")
	public String muestraFormulario(Model model) {
	model.addAttribute("bus", new Bus());
	return "registrobus";
	}
	
	@PostMapping("/newBus/submit")
	public String processForm(@ModelAttribute("bus")Bus bus) {
		service.addBus(bus);
		
		return "redirect:/index";
		
		
	}
	
}
