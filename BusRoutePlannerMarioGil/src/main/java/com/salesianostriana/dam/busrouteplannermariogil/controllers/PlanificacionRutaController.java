package com.salesianostriana.dam.busrouteplannermariogil.controllers;

	
	import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;
	import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
	import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
	import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
	import com.salesianostriana.dam.busrouteplannermariogil.service.PlanificacionRutaService;
	import com.salesianostriana.dam.busrouteplannermariogil.service.RouteService;
	import com.salesianostriana.dam.busrouteplannermariogil.service.BusService;
	import com.salesianostriana.dam.busrouteplannermariogil.service.DriverService;

	import lombok.RequiredArgsConstructor;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.*;

	import java.util.Optional;
	@Controller
	@RequestMapping("/planificacionRuta")
	@RequiredArgsConstructor
	public class PlanificacionRutaController {


	private final PlanificacionRutaService prservicio;
	    private final RouteService routeservice;
	    private final BusService busservice;
	    private final DriverService driverservice;

	    @GetMapping
	    public String listarPlanificaciones(Model model) {
	        model.addAttribute("planificaciones", prservicio.findAll());
	        return "listaPlanificaciones";
	    }

	    @GetMapping("/nuevaPlanificacionRuta")
	    public String mostrarFormulario(Model model) {
	        PlanificacionRuta nueva = new PlanificacionRuta();
	        nueva.setRoute(new Route());
	        nueva.setBus(new Bus());
	        nueva.setDriver(new Driver());
	        model.addAttribute("planificacion", nueva);
	        model.addAttribute("rutas", routeservice.findAll());
	        model.addAttribute("buses", busservice.findAll());
	        model.addAttribute("conductores", driverservice.findAll());
	        return "formPlanificacionRuta";
	    }

	    @PostMapping("/guardarPlanificacionRuta")
	    public String guardar(@ModelAttribute("planificacion") PlanificacionRuta planificacion) {
	        prservicio.save(planificacion);
	        return "redirect:/planificacionRuta";
	    }
	    
	    @GetMapping("/editar/{id}")
	    public String editarPlanificacionRuta(@PathVariable("id") Long id, Model model) {
	        Optional<PlanificacionRuta> pr = prservicio.findById(id);
	       
	        if (pr.isPresent()) {
	            model.addAttribute("planificacion", pr.get());
	            model.addAttribute("rutas", routeservice.findAll());
	            model.addAttribute("buses", busservice.findAll());
	            model.addAttribute("conductores", driverservice.findAll());
	            return "formPlanificacionRuta";
	           
	        }
	       
	        return "redirect:/planificacionRuta";
	    }
	    
	    @GetMapping("/borrar/{id}")
	    public String borrarPlanificacionRuta(@PathVariable("id") Long id) {
	        prservicio.deleteById(id);
	        return "redirect:/planificacionRuta";
	    }

	}
