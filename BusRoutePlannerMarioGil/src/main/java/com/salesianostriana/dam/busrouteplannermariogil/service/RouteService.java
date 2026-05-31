package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.exceptions.RutaSolapadaException;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.RouteRepository;
import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RouteService extends BaseServiceImplem<Route, Long, RouteRepository> {

	private final RouteRepository repository;

	//@Transactional
	public void saveRuta(Route r) {
		boolean codigoDuplicado = repository.existsById(r.getCodigo());

        if (codigoDuplicado) {
            // Si el código ya existe en la base de datos, lanzamos tu excepción
            throw new RutaSolapadaException(
                String.format("No se puede guardar la ruta. El código '%d' ya está asignado a otra ruta existente.", 
                r.getCodigo())
            );
        }
		repository.save(r);
	}

	public List<Route> findAll() {
		return repository.findAll();
	}

 
}