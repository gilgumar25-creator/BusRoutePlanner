package com.salesianostriana.dam.busrouteplannermariogil.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull(message = "El código de la ruta es obligatorio")
    @Min(value = 1, message = "El código debe ser un número positivo")
    private Long codigo;
 
    @NotBlank(message = "El origen no puede estar vacío")
    @Size(max = 100, message = "El origen no puede superar los 100 caracteres")
    private String origen;
 
    @NotBlank(message = "El destino no puede estar vacío")
    @Size(max = 100, message = "El destino no puede superar los 100 caracteres")
    private String destino;
 
    @DecimalMin(value = "0.1", message = "La distancia debe ser mayor que 0")
    private double distancia;
 
    @DecimalMin(value = "0.01", message = "El precio por kilómetro debe ser mayor que 0")
    private double preciokilometro;
 
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio del viaje no puede ser negativo")
    private double precioViaje;
 
    @Min(value = 1, message = "El número máximo de buses simultáneos tiene que ser mínimo 1")
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
