package com.salesianostriana.dam.busrouteplannermariogil.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Driver;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.DriverRepository;
import com.salesianostriana.dam.busrouteplannermariogil.servicebase.BaseServiceImplem;

@Service
public class DriverService extends BaseServiceImplem<Driver,Long,DriverRepository>{
	public List<Driver> getLista () {
		return Arrays.asList(
				new Driver(298651L,"Mario"),
				new Driver(573917L,"Daniel")
				);

	}
private List <Driver> listaDrivers = new ArrayList <Driver>();
	
	public void addDriver (Driver d) {
		listaDrivers.add(d);
	
	}
	
	public List<Driver> getlistaDrivers (){
		return listaDrivers; 
	}
}