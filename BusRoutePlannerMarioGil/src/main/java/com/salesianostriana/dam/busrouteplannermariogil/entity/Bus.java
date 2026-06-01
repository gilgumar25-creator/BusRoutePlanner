package com.salesianostriana.dam.busrouteplannermariogil.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.ForeignKey;


@Data @NoArgsConstructor @AllArgsConstructor 
@Entity @Builder
public class Bus {
	
	@Id 
    @NotNull(message = "La matrícula es obligatoria")
    @Min(value = 1, message = "La matrícula debe ser un número positivo")
	private Long matricula;
	
    @Min(value = 1, message = "La capacidad debe ser como mínimo 1 pasajero")
	private int capacidad;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_route",
		foreignKey = @ForeignKey(name = "fk_bus_route"))
	private Route route;
	
	@OneToMany(mappedBy = "bus", fetch = FetchType.EAGER)
	@ToString.Exclude
	@Builder.Default
	private List<Driver> drivers = new ArrayList<>();
	

}
