package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.service.DriverService;


@Controller
public class DriverController {
	
private final DriverService service;

public DriverController(DriverService servicio) {
	this.service = servicio;
}


	@GetMapping("/Driver")
	public String showBuses(Model model) {
		Driver C2 = new Driver(
				);
		
		model.addAttribute("driver",C2);
		model.addAttribute("mensajeBienvenida","Muy buenas, te presentamos la mejor página web de rutas de autobuses del mundo, próximamente la Junta de Andalucía comprará esta página jeje");
		
		return "drivers";
	}
		
	@GetMapping("/listaDrivers")
	public String showListaBuses(Model model) {
		
		model.addAttribute("driversList",service.getLista());
		
		return "listaDrivers";
	
	}
	
	@GetMapping("/newDriver")
	public String muestraFormulario(Model model) {
	model.addAttribute("driver", new Driver());
	return "registroDriver";
	}
	
	@PostMapping("/newDriver/submit")
	public String processForm(@ModelAttribute("driver")Driver driver) {
		service.addDriver(driver);
		
		return "redirect:/index";
		
		
	}
}