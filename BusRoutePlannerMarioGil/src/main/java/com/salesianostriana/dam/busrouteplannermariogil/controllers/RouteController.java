package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.service.RouteService;

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

    @GetMapping("/new")
    public String nuevaRuta(Model model) {
        model.addAttribute("route", new Route());
        return "formRuta";
    }

    @PostMapping("/guardarRuta/submit")
    public String submitNuevaRuta(@ModelAttribute("route") Route route) {
        //service.save(route);
    	service.saveRuta(route);
        

        return "redirect:/listaRutas";
    }
    
	@GetMapping("/editar/{id}")
	public String editarRuta(@PathVariable("id") Long id, Model model) {

		Optional<Route> route = service.findById(id);

		if (route.isPresent()) {
			model.addAttribute("route", route);
			return "formRuta";
		} else {
			return "redirect:/listaRutas";
		}
	}
	
	@GetMapping("/borrar/{id}")
	public String borrarProducto(@PathVariable("id") Long id, Model model) {

		Optional<Route> route = service.findById(id);

		if (route.isPresent()) {
			service.delete(route.get());
		}
		return "redirect:/listaRutas";
	}

    @GetMapping("/Registro")
    public String register(Model model) {
        return "registrousuario";
    }
}
