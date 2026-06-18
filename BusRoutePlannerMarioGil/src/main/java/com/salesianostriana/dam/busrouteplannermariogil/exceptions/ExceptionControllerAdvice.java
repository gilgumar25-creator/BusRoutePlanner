package com.salesianostriana.dam.busrouteplannermariogil.exceptions;

import java.util.NoSuchElementException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;

@ControllerAdvice
public class ExceptionControllerAdvice {

	// 1. Manejo de nuestra excepción personalizada
	@ExceptionHandler(RutaSolapadaException.class)
	public String handleCodigoRepetido(RutaSolapadaException ex, Model model) {
		model.addAttribute("errorTitulo", "Error de Asignación");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "errorRuta";
	}

	@ExceptionHandler(CapacidadExcedidaException.class)
	public String handleSuperadasPlazas(CapacidadExcedidaException ex, Model model) {
		model.addAttribute("errorTitulo", "Error de Asignación");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "errorBus";
	}
	
	@ExceptionHandler(DriverDuplicadoException.class)
	public String handleSuperadasPlazas(DriverDuplicadoException ex, Model model) {
		model.addAttribute("errorTitulo", "Error de Asignación");
		model.addAttribute("errorMensaje", ex.getMessage());
		return "errorPlanificacion";
	}
	
	@ExceptionHandler(BusDuplicadoException.class)
    public String handleBusDuplicado(BusDuplicadoException ex, Model model) {
        model.addAttribute("errorTitulo", "Conflicto de Autobús");
        model.addAttribute("errorMensaje", ex.getMessage());
        return "errorBusDoble";
    }
	
	

	// 2. Manejo de una excepción propia del API de Java
	// (Ej: Buscar un ID que no existe)
	/*@ExceptionHandler(NoSuchElementException.class)
	public String handleNotFound(NoSuchElementException ex, Model model) {
		model.addAttribute("errorTitulo", "Elemento No Encontrado");
		model.addAttribute("errorMensaje", "El curso solicitado no existe en nuestra base de datos.");
		return "error";
	}*/

	// 3. Manejo de otra excepción del API de Java
	// (Argumento ilegal o inválido en lógica de negocio)

	/*@ExceptionHandler(IllegalArgumentException.class)
	 public String handleIllegalArgument(IllegalArgumentException ex, Model model)
	 {
	 model.addAttribute("errorTitulo", "Operación Inválida");
	 model.addAttribute("errorMensaje", ex.getMessage());
	 return "error";
	 }*/

	// PREGUNTAR A ÁNGEL SI PUEDO IMPLEMENTAR ESTA FUNCIONALIDAD
	/*@ExceptionHandler(IllegalArgumentException.class)
	public String handleIllegalArgument(IllegalArgumentException ex, Model model,
			@RequestHeader(value = "referer", required = false) String refererUrl) {

		model.addAttribute("errorTitulo", "Operación Inválida");
		model.addAttribute("errorMensaje", ex.getMessage());

		// Si sabemos de qué URL viene y contiene la palabra "bus"
		if (refererUrl != null && refererUrl.contains("/nuevoBus")) {
			return "errorBus"; // Muestra la pantalla de autobús con su botón correspondiente
		}

		// Por defecto, ante cualquier otro caso, va a la de rutas
		return "errorRuta";

	} */

}