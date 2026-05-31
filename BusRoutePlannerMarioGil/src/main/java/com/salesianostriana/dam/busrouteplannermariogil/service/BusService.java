package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.exceptions.CapacidadExcedidaException;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.BusRepository;

import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BusService extends BaseServiceImplem<Bus, Long, BusRepository> {

	private final BusRepository repository;

	//@Transactional
	public void saveBus(Bus b) {
		if (b.getCapacidad() > 100) {
	        throw new CapacidadExcedidaException("No se pueden crear autobuses con más de 100 plazas.");
	    }
	    
	    if (b.getCapacidad() <= 0) {
	        throw new CapacidadExcedidaException("La capacidad debe ser como mínimo de 1 pasajero.");
	    }
		repository.save(b);
	}

	public List<Bus> findAll() {
		return repository.findAll();
	}
}