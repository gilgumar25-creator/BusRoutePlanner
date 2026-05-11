package com.salesianostriana.dam.busrouteplannermariogil.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Route {
	
	@Id
	private Integer codigo;
	
	private String origen;
	
	private String destino;
	
	private int numeroMaximoBusesSimultaneos;
	
	

}
