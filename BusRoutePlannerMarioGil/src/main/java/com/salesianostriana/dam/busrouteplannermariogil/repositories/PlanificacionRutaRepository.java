package com.salesianostriana.dam.busrouteplannermariogil.repositories;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;

public interface PlanificacionRutaRepository extends JpaRepository <PlanificacionRuta,Long>{
	
	boolean existsByDriverAndDiaSemana(Driver driver, LocalDate diaSemana);

	@Query("SELECT p.route FROM PlanificacionRuta p GROUP BY p.route ORDER BY COUNT(p.route) DESC")
	List<PlanificacionRuta> findRutasMasUsadas();
	
	List<PlanificacionRuta> findByDiaSemana2(DayOfWeek diaSemana2);
	
	
	
	
	

}