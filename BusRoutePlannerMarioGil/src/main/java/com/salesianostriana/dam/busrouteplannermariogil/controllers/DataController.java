package com.salesianostriana.dam.busrouteplannermariogil.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.dam.busrouteplannermariogil.service.DataService;

@Controller
@RequestMapping("/admin/data")
public class DataController {

	@Autowired
    private DataService dataService;

    @GetMapping("/datos")
    public String verPanelData(Model model) {
        
        model.addAttribute("gananciaTotal", dataService.calcularGananciaTotal());
        model.addAttribute("gananciasPorRuta", dataService.calcularGananciasPorRuta());
        model.addAttribute("mediaRatioKm", dataService.calcularMediaPrecioKilometro());

        return "admin/data";
    }
}
