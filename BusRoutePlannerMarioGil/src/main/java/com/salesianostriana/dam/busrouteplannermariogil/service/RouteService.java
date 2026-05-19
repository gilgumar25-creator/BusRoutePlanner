package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
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
		repository.save(r);
	}

	public List<Route> findAll() {
		return repository.findAll();
	}
}