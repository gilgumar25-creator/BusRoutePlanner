package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Bus;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.BusRepository;
import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

@Service
public class BusService extends BaseServiceImplem<Bus,Long,BusRepository>{
	public List<Bus> getLista () {
		return Arrays.asList(
				Bus.builder()
	            .matricula(58896546L)
	            .capacidad(100)
	            .build(),
	        Bus.builder()
	            .matricula(83819128L)
	            .capacidad(97)
	            .build()
	    );

	}
private List <Bus> listaBuses = new ArrayList <Bus>();
	
	public void addBus (Bus b) {
		listaBuses.add(b);
	
	}
	
	public List<Bus> getlistaBuses (){
		return listaBuses; 
	}
}