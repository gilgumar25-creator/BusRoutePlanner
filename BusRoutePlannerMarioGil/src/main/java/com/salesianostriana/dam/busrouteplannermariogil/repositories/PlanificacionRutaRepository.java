package com.salesianostriana.dam.busrouteplannermariogil.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;

public interface PlanificacionRutaRepository extends JpaRepository <PlanificacionRuta,Long>{
	
	boolean existsByDriverAndDiaSemana(Driver driver, LocalDate diaSemana);
	

}