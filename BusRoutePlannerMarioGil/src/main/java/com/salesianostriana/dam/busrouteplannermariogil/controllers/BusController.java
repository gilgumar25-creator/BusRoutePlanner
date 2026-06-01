package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.service.BusService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bus")
public class BusController {
	
private final BusService service;

public BusController(BusService servicio) {
	this.service = servicio;
}

	@GetMapping("listaBuses")
	public String showListaBuses(Model model) {
		
		model.addAttribute("busList",service.findAll());
		
		return "listaBuses";
	
	}
	
	@GetMapping("nuevoBus")
	public String muestraFormulario(Model model) {
	model.addAttribute("bus", new Bus());
	return "formBus";
	}
	
	@PostMapping("guardarBus/submit")
	public String submitNuevoBus(@Valid @ModelAttribute("bus") Bus bus,
            BindingResult bindingResult) {
		
        if (bindingResult.hasErrors()) {
            return "formBus";
        }

		service.saveBus(bus);
		return "redirect:/bus/listaBuses";
		
		
	}
	
	@GetMapping("editar/{matricula}")
	public String editarBus(@PathVariable("matricula") Long matricula, Model model) {

		Optional<Bus> bus = service.findById(matricula);

		if (bus.isPresent()) {
			model.addAttribute("bus", bus.get());
			return "formBus";
		} else {
			return "redirect:/bus/listaBuses";
		}
	}

	@GetMapping("borrar/{matricula}")
	public String borrarProducto(@PathVariable("matricula") Long matricula, Model model) {

		Optional<Bus> bus = service.findById(matricula);

		if (bus.isPresent()) {
			service.delete(bus.get());
		}
		return "redirect:/bus/listaBuses";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentBus(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorTitulo", "Operación Inválida en Autobús");
        model.addAttribute("errorMensaje", ex.getMessage());
        return "errorBus"; 
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFoundBus(NoSuchElementException ex, Model model) {
        model.addAttribute("errorTitulo", "Autobús No Encontrado");
        model.addAttribute("errorMensaje", "El autobús con la matrícula solicitada no está registrado.");
        return "errorBus"; 
    }
}
