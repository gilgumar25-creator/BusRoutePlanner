package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.RouteRepository;
import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;



@Service
public class RouteService extends BaseServiceImplem<Route,Long,RouteRepository>{
	public List<Route> getLista () {
		return Arrays.asList(
				new Route( "Palomares del Río","República Argentina",2),
				new Route( "Palomares del Río","Blas Infante",3)
				);

	}
private List <Route> listaRutas = new ArrayList <Route>();
	
	public void addRuta (Route r) {
		listaRutas.add(r);
	
	}
	
	public List<Route> getlistaRutas (){
		return listaRutas; 
	}
	
	
	
	

}