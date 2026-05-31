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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Route {

	@Id
	private Long codigo;

	private String origen;

	private String destino;
	
	private double distancia;
	
	private double preciokilometro;
	
	private double precioViaje;

	// @Min(value = 1, message = "El nºmáximo de buses simultáneos tiene que ser
	// mínimo 1")
	private int numeroMaximoBusesSimultaneos;

	
	public Route(Long codigo, String origen, String destino, double distancia, double preciokilometro,
			double precioViaje, int numeroMaximoBusesSimultaneos) {
		super();
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.preciokilometro = preciokilometro;
		this.precioViaje = precioViaje;
		this.numeroMaximoBusesSimultaneos = numeroMaximoBusesSimultaneos;
	}


	@OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Bus> buses = new ArrayList<>();

}
