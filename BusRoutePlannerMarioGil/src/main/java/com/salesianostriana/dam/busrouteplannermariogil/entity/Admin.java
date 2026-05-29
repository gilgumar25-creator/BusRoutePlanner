package com.salesianostriana.dam.busrouteplannermariogil.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) 
@Entity
public class Admin extends Usuario{
	
	private LocalDate fechaNombramiento;

	public Admin(Integer id, String nombre, String password, Rol rol,LocalDate fechaNombramiento) {
		super(id, nombre, password, rol);
		this.fechaNombramiento = fechaNombramiento;
	}
	
	

}
