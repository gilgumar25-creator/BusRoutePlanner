package com.salesianostriana.dam.busrouteplannermariogil.service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.DriverRepository;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.PlanificacionRutaRepository;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired 
    private PlanificacionRutaRepository planificacionRutaRepository;

	public double calcularGananciaTotal() {
		return routeRepository.findAll().stream()
				.mapToDouble(r -> r.getPrecioViaje() * (r.getBuses() != null ? r.getBuses().size() : 0)).sum();
	}

	public Map<String, Double> calcularGananciasPorRuta() {
		return planificacionRutaRepository.findAll().stream()
				.collect(Collectors.groupingBy(
                        p -> String.valueOf(p.getRoute().getCodigo()), 
                        Collectors.summingDouble(p -> p.getRoute().getPrecioViaje())
                ));
	}

	public double calcularMediaPrecioKilometro() {
		return routeRepository.findAll().stream().filter(r -> r.getDistancia() > 0)
				.mapToDouble(r -> r.getPrecioViaje() / r.getDistancia()).average().orElse(0.0);
	}
}