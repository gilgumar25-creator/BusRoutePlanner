package com.salesianostriana.dam.busrouteplannermariogil.security;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.busrouteplannermariogil.entity.Admin;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Operador;
import com.salesianostriana.dam.busrouteplannermariogil.entity.PlanificacionRuta;
import com.salesianostriana.dam.busrouteplannermariogil.entity.Rol;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.PlanificacionRutaRepository;
import com.salesianostriana.dam.busrouteplannermariogil.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PlanificacionRutaRepository planificacionRutaRepository;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByNombre("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setNombre("admin");
            admin.setPassword("{noop}admin");
            admin.setRol(Rol.ADMIN);
            admin.setFechaNombramiento(LocalDate.of(2024, 1, 1));
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByNombre("operador").isEmpty()) {
            Operador operador = new Operador();
            operador.setNombre("operador");
            operador.setPassword("{noop}operador");
            operador.setRol(Rol.OPERADOR);
            operador.setActivo(true);
            usuarioRepository.save(operador);
        }
        
        
        PlanificacionRuta pr = planificacionRutaRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("No hay pr con id 1"));
        System.out.println(pr);

        pr.setDiaSemana2(DayOfWeek.FRIDAY);
        pr.setDiaSemana(LocalDate.now());
        
        planificacionRutaRepository.save(pr);
        
        pr = planificacionRutaRepository.findById(1L).orElseThrow(() -> new EntityNotFoundException("No hay pr con id 1"));
        
        System.out.println(pr);
        
        
        
    }
    
    
}
