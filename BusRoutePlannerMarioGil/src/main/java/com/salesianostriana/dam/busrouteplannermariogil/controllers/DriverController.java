package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.service.DriverService;


@Controller
@RequestMapping("/driver")
public class DriverController {


	private final DriverService service;


	public DriverController(DriverService service) {

		this.service = service;

	}


	@GetMapping("/listaDrivers")
<<<<<<< HEAD

=======
>>>>>>> develop
	public String listarDrivers(Model model) {

		model.addAttribute("driversList", service.findAll());

		return "listaDrivers";

	}


	@GetMapping("/nuevoDriver")

	public String nuevoDriver(Model model) {

		model.addAttribute("driver", new Driver());

		return "formDriver";

	}


	@PostMapping("/guardarDriver/submit")

	public String submitNuevaRuta(@ModelAttribute("driver") Driver driver) {

		service.save(driver);


		return "redirect:/driver/listaDrivers";

	}


	@GetMapping("/editar/{licencia}")

	public String editarRuta(@PathVariable("licencia") Long licencia, Model model) {


		Optional<Driver> driver = service.findById(licencia);


		if (driver.isPresent()) {

			model.addAttribute("driver", driver.get());

			return "formDriver";


		} else {

			return "redirect:/driver/listaDrivers";

		}

	}

	@GetMapping("/borrar/{licencia}")

	public String borrarProducto(@PathVariable("licencia") Long licencia, Model model) {


		Optional<Driver> driver = service.findById(licencia);


		if (driver.isPresent()) {

			service.delete(driver.get());

		}

		return "redirect:/driver/listaDrivers";

	}

}