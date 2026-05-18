package com.salesianostriana.dam.busrouteplannermariogil.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder
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
	
	@OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Bus> buses = new ArrayList<>();
	
	
	
	

}
