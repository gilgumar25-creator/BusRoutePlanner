package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;
import com.salesianostriana.dam.busrouteplannermariogil.exceptions.DriverDuplicadoException;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.PlanificacionRutaRepository;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.DriverRepository;
import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PlanificacionRutaService extends BaseServiceImplem<PlanificacionRuta, Long, PlanificacionRutaRepository> {

private final PlanificacionRutaRepository repository;
private final DriverRepository DriverRepository;

//@Transactional
public void savePlanificacion(PlanificacionRuta pr) {
	
	boolean yaAsignado = repository.existsByDriverAndDiaSemana(pr.getDriver(), pr.getDiaSemana());

        if (yaAsignado) {
        	Driver conductor = DriverRepository.findById(pr.getDriver().getLicencia())
                    .orElse(pr.getDriver());
        	throw new DriverDuplicadoException(
                    "El conductor " + conductor.getNombre() +
                    " ya cuenta con un turno asignado para el día " + pr.getDiaSemana() + 
                    ". No se le permite doblar turno (mañana y tarde) el mismo día."
                );
        }
	
repository.save(pr);
}

public List<PlanificacionRuta> findAll() {
return repository.findAll();
}
}
