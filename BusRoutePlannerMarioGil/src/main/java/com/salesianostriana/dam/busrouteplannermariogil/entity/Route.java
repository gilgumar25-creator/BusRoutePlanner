package com.salesianostriana.dam.busrouteplannermariogil.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Route {
	
	@Id @GeneratedValue
	private Long codigo;
	
	private String origen;
	
	private String destino;
	
	private int numeroMaximoBusesSimultaneos;

	public Route(String origen, String destino, int numeroMaximoBusesSimultaneos) {
		super();
		this.origen = origen;
		this.destino = destino;
		this.numeroMaximoBusesSimultaneos = numeroMaximoBusesSimultaneos;
	}
	
	
	
	

}
