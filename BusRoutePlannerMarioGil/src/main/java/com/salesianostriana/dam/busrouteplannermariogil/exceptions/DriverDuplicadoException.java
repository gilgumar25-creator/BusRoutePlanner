package com.salesianostriana.dam.busrouteplannermariogil.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DriverDuplicadoException extends RuntimeException {
	public DriverDuplicadoException(String mensaje) {
		super(mensaje);
	}

}
