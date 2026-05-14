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

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity @Builder
public class Bus {
	
	@Id @GeneratedValue
	private Long matricula;
	
	private int capacidad;
	
	/*@OneToMany(mappedBy = "bus", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Driver> drivers = new ArrayList<>();*/
	
	

}
