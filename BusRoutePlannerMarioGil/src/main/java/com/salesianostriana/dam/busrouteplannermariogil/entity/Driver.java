package com.salesianostriana.dam.busrouteplannermariogil.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity@Builder
public class Driver {

    @Id
    @NotNull(message = "El número de licencia es obligatorio")
    @Min(value = 1, message = "La licencia debe ser mayor que 0")
    private Long licencia;
 
    @NotBlank(message = "El nombre del conductor es obligatorio")
    @Size(min = 2, max = 80, message = "El nombre debe tener entre 2 y 80 caracteres")
    private String nombre;
 
    @NotNull(message = "Debes seleccionar obligatoriamente si el conductor está activo o inactivo")
    private Boolean estado;

	public Driver(
			@NotNull(message = "El número de licencia es obligatorio") @Min(value = 1, message = "La licencia debe ser mayor que 0") Long licencia,
			@NotBlank(message = "El nombre del conductor es obligatorio") @Size(min = 2, max = 80, message = "El nombre debe tener entre 2 y 80 caracteres") String nombre,
			@NotNull(message = "Debes seleccionar obligatoriamente si el conductor está activo o inactivo") Boolean estado) {
		super();
		this.licencia = licencia;
		this.nombre = nombre;
		this.estado = estado;
	}




	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bus",
		foreignKey = @ForeignKey(name = "fk_driver_bus"))
	private Bus bus;
	

	

}
