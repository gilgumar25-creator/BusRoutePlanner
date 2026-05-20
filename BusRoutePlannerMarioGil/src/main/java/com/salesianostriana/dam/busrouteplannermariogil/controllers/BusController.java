package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.service.BusService;



@Controller
public class BusController {
	
private final BusService service;

public BusController(BusService servicio) {
	this.service = servicio;
}

	@GetMapping("/listaBuses")
	public String showListaBuses(Model model) {
		
		model.addAttribute("busList",service.findAll());
		
		return "listaBuses";
	
	}
	
	@GetMapping("/nuevoBus")
	public String muestraFormulario(Model model) {
	model.addAttribute("bus", new Bus());
	return "formBus";
	}
	
	@PostMapping("/guardarBus/submit")
	public String submitNuevoBus(@ModelAttribute("bus")Bus bus) {
		service.save(bus);
		return "redirect:/listaBuses";
		
		
	}
	
	@GetMapping("/editar/{matricula}")
	public String editarRuta(@PathVariable("matricula") Long matricula, Model model) {

		Optional<Bus> route = service.findById(matricula);

		if (route.isPresent()) {
			model.addAttribute("route", route.get());
			return "formBus";
		} else {
			return "redirect:/listaBuses";
		}
	}

	@GetMapping("/borrar/{matricula}")
	public String borrarProducto(@PathVariable("matricula") Long matricula, Model model) {

		Optional<Bus> route = service.findById(matricula);

		if (route.isPresent()) {
			service.delete(route.get());
		}
		return "redirect:/listaBuses";
	}
}
