package com.salesianostriana.dam.busrouteplannermariogil.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	Optional<Usuario> findByNombre(String nombre);

}
