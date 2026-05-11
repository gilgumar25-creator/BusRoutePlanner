package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Route;



@Service
public class RouteService {
	public List<Route> getLista () {
		return Arrays.asList(
				new Route(152, "Palomares del Río","República Argentina",2),
				new Route(153, "Palomares del Río","Blas Infante",3)
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