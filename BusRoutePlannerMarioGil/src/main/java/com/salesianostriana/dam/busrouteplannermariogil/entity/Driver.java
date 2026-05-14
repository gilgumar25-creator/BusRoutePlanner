package com.salesianostriana.dam.busrouteplannermariogil.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity@Builder
public class Driver {

	@Id @GeneratedValue
	private Long licencia;
	
	private String nombre;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bus",
		foreignKey = @ForeignKey(name = "fk_driver_bus"))
	private Bus bus;
	

	

}
