package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
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
		
		if (pr.getDiaSemana() != null) {
	        pr.setDiaSemana2(pr.getDiaSemana().getDayOfWeek());
	    }

		boolean yaAsignado = repository.existsByDriverAndDiaSemana(pr.getDriver(), pr.getDiaSemana());

		if (yaAsignado) {
			Driver conductor = DriverRepository.findById(pr.getDriver().getLicencia()).orElse(pr.getDriver());
			throw new DriverDuplicadoException(
					"El conductor " + conductor.getNombre() + " ya cuenta con un turno asignado para el día "
							+ pr.getDiaSemana() + ". No se le permite doblar turno (mañana y tarde) el mismo día.");
		}
		
		boolean busOcupado = repository.existsByBusAndDiaSemanaAndHorario(pr.getBus(), pr.getDiaSemana(), pr.getHorario());

        if (busOcupado) {
            throw new com.salesianostriana.dam.busrouteplannermariogil.exceptions.BusDuplicadoException(
                    "El autobús seleccionado ya se encuentra asignado para el día " 
                            + pr.getDiaSemana() + " en el horario de " + pr.getHorario() + ".");
        }

		repository.save(pr);
	}

	public List<PlanificacionRuta> findAll() {
		return repository.findAll();
	}
	
	public List<Object[]> findByRutasMasUsadas() {
		return repository.findRutasMasUsadas();
	}
	
	public List<PlanificacionRuta> buscarPorDiaDeLaSemana(DayOfWeek diaSemana2) {
	    if (diaSemana2 == null) {
	        throw new IllegalArgumentException("El día de la semana no puede ser nulo");
	    }
	    
	    return repository.findByDiaSemana2(diaSemana2);
	}
}
