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

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.service.RouteService;

import jakarta.validation.Valid;

@Controller
public class RouteController {

	private final RouteService service;

	public RouteController(RouteService service) {
		this.service = service;
	}

	@GetMapping("/listaRutas")
	public String listarRutas(Model model) {
		model.addAttribute("routesList", service.findAll());
		return "listaRutas";
	}

	@GetMapping("/nuevaRuta")
	public String nuevaRuta(Model model) {
		model.addAttribute("route", new Route());
		return "formRuta";
	}

	@PostMapping("/guardarRuta/submit")
	public String submitNuevaRuta(@Valid @ModelAttribute("route") Route route,
            BindingResult bindingResult) {
		
        if (bindingResult.hasErrors()) {
            return "formRuta";
        }

        boolean esEdicion = service.findById(route.getCodigo()).isPresent();
		service.saveRuta(route,esEdicion);

		return "redirect:/listaRutas";
	}

	@GetMapping("/editar/{codigo}")
	public String editarRuta(@PathVariable("codigo") Long codigo, Model model) {

		Optional<Route> route = service.findById(codigo);

		if (route.isPresent()) {
			model.addAttribute("route", route.get());
			return "formRuta";

		} else {
			return "redirect:/listaRutas";
		}
	}
	
	@GetMapping("/borrar/{codigo}")
	public String borrarRuta(@PathVariable("codigo") Long codigo, Model model) {

		Optional<Route> route = service.findById(codigo);

		if (route.isPresent()) {
			service.delete(route.get());
		}
		return "redirect:/listaRutas";
	}
	
	
	@ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentRoute(IllegalArgumentException ex, Model model) {
        model.addAttribute("errorTitulo", "Operación Inválida en Ruta");
        model.addAttribute("errorMensaje", ex.getMessage());
        return "errorRuta"; 
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFoundRoute(NoSuchElementException ex, Model model) {
        model.addAttribute("errorTitulo", "Ruta No Encontrada");
        model.addAttribute("errorMensaje", "La ruta que estás intentando buscar o editar no existe.");
        return "errorRuta"; 
    }
}
