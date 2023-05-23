package com.example.jgp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jgp.dto.StanicaDTO;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/stanica")
public class StanicaController {
    
    @Autowired
    StanicaService stanicaService;

    @Autowired
    ZonaService zonaService;

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void update(StanicaDTO update_stanica, HttpServletResponse response) {
        Stanica stanica = new Stanica();
        System.out.println("Stanica" + update_stanica.getZonaId());
        stanica.setId(Long.valueOf(update_stanica.getId()));
        stanica.setNaziv(update_stanica.getNaziv());
        stanica.setLokacija(update_stanica.getLokacija());
        stanica.setZona(zonaService.findById(Long.valueOf(update_stanica.getZonaId())).get());
        stanicaService.update(stanica);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
