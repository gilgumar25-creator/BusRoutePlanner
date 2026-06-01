package com.salesianostriana.dam.busrouteplannermariogil.controllers;

	
	import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.service.BusService;
import com.salesianostriana.dam.busrouteplannermariogil.service.DriverService;
import com.salesianostriana.dam.busrouteplannermariogil.service.PlanificacionRutaService;
import com.salesianostriana.dam.busrouteplannermariogil.service.RouteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
	    public String guardar(@Valid @ModelAttribute("planificacion") PlanificacionRuta planificacion,
                BindingResult bindingResult, Model model) {
	    	
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("rutas", routeservice.findAll());
	            model.addAttribute("buses", busservice.findAll());
	            model.addAttribute("conductores", driverservice.findAll());
	            return "formPlanificacionRuta";
	        }

	        prservicio.savePlanificacion(planificacion);
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
	    
	    
	    
	   /* @GetMapping("/listaPlanificaiones/viajeporfecha")
		public String listarViajePorFecha(Model model) {
	    	
	    	List<PlanificacionRuta> fecha = prservicio.findByFecha(LocalDate.now());
	    	
			model.addAttribute("planificaciones", fecha);
			
			return "listaDrivers";
		}*/
	    
	    @GetMapping("/porDia")
	    public String listarPorDia(@RequestParam("nombre") DayOfWeek nombre, Model model) {
	        // Llamamos a tu servicio pasándole el Enum
	        List<PlanificacionRuta> filtradas = prservicio.buscarPorDiaDeLaSemana(nombre);
	        
	        // Volvemos a llenar el modelo para la misma vista de listado
	        model.addAttribute("planificaciones", filtradas);
	        // Opcional: mandamos el día seleccionado por si quieres mostrar un título dinámico en el HTML
	        model.addAttribute("diaSeleccionado", nombre); 
	        
	        return "listaPlanificaciones"; 
	    }
	    
	    
	    
	    @ExceptionHandler(IllegalArgumentException.class)
	    public String handleIllegalArgumentBus(IllegalArgumentException ex, Model model) {
	        model.addAttribute("errorTitulo", "Operación Inválida Planificación");
	        model.addAttribute("errorMensaje", ex.getMessage());
	        return "errorPlanificacion"; 
	    }

	    @ExceptionHandler(NoSuchElementException.class)
	    public String handleNotFoundBus(NoSuchElementException ex, Model model) {
	        model.addAttribute("errorTitulo", "Autobús No Encontrado");
	        model.addAttribute("errorMensaje", "La planificación no existe.");
	        return "errorPlanificacion"; 
	    }

	}
