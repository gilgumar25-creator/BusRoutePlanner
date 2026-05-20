package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;

import com.salesianostriana.dam.busrouteplannermariogil.repositories.BusRepository;

import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BusService extends BaseServiceImplem<Bus, Long, BusRepository> {

	private final BusRepository repository;

	//@Transactional
	public void saveRuta(Bus b) {
		repository.save(b);
	}

	public List<Bus> findAll() {
		return repository.findAll();
	}
}