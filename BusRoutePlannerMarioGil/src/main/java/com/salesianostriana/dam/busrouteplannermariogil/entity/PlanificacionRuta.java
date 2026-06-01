package com.salesianostriana.dam.busrouteplannermariogil.entity;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanificacionRuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
 
    @NotNull(message = "La fecha del turno es obligatoria")
    @FutureOrPresent(message = "La fecha del turno no puede ser anterior a hoy")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate diaSemana;
    
    @Enumerated(EnumType.STRING)
    private DayOfWeek diaSemana2;
 
    @NotNull(message = "El horario es obligatorio")
    @Enumerated(EnumType.STRING)
    private Horario horario;
 
    @NotNull(message = "Debes seleccionar una ruta")
    @ManyToOne
    @JoinColumn(name = "route_codigo")
    private Route route;
 
    @NotNull(message = "Debes seleccionar un autobús")
    @ManyToOne
    @JoinColumn(name = "bus_matricula")
    private Bus bus;
 
    @NotNull(message = "Debes seleccionar un conductor")
    @ManyToOne
    @JoinColumn(name = "driver_licencia")
    private Driver driver;

}