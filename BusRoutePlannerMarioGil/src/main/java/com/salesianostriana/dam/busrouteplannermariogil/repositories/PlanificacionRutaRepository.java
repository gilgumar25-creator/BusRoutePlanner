package com.salesianostriana.dam.busrouteplannermariogil.repositories;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Horario;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;


public interface PlanificacionRutaRepository extends JpaRepository<PlanificacionRuta, Long> {

	boolean existsByDriverAndDiaSemana(Driver driver, LocalDate diaSemana);
	
	// Añade o edita esta línea dentro de tu interfaz PlanificacionRutaRepository
	boolean existsByBusAndDiaSemanaAndHorario(Bus bus, LocalDate diaSemana,Horario horario);

	@Query("SELECT p.route, COUNT(p.route) FROM PlanificacionRuta p GROUP BY p.route ORDER BY COUNT(p.route) DESC")
	List<Object[]> findRutasMasUsadas();

	List<PlanificacionRuta> findByDiaSemana2(DayOfWeek diaSemana2);

}