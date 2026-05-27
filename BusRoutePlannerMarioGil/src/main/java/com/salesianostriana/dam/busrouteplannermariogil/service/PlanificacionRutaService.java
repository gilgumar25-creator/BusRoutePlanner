package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.PlanificacionRutaRepository;
import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PlanificacionRutaService extends BaseServiceImplem<PlanificacionRuta, Long, PlanificacionRutaRepository> {

private final PlanificacionRutaRepository repository;

//@Transactional
public void savePlanificacion(PlanificacionRuta pr) {
repository.save(pr);
}

public List<PlanificacionRuta> findAll() {
return repository.findAll();
}
}
