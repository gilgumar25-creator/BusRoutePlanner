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
<<<<<<< HEAD
	
	@Id 
=======

	@Id
>>>>>>> 26-fe12-editruta
	private Long codigo;

	private String origen;

	private String destino;

	// @Min(value = 1, message = "El nºmáximo de buses simultáneos tiene que ser
	// mínimo 1")
	private int numeroMaximoBusesSimultaneos;
<<<<<<< HEAD
	
=======

>>>>>>> 26-fe12-editruta
	public Route(Long codigo, String origen, String destino, int numeroMaximoBusesSimultaneos) {
		super();
		this.codigo = codigo;
		this.origen = origen;
		this.destino = destino;
		this.numeroMaximoBusesSimultaneos = numeroMaximoBusesSimultaneos;
	}

<<<<<<< HEAD


=======
>>>>>>> 26-fe12-editruta
	@OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Bus> buses = new ArrayList<>();

}
