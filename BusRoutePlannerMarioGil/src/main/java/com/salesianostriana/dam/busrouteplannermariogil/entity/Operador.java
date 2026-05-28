package com.salesianostriana.dam.busrouteplannermariogil.entity;


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
public class Operador extends Usuario{
	
	
	private boolean activo;

	public Operador(Integer id, String nombre, String password, Rol rol,boolean activo) {
		super();
		this.activo = activo;
	}
	
	

}
