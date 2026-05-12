package com.salesianostriana.dam.busrouteplannermariogil.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Driver {

	
	private String nombre;
	
	@Id
	private Integer licencia;
	
	

}
