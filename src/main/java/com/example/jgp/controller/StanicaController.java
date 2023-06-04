package com.example.jgp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jgp.dto.StanicaDTO;
import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/stanica")
public class StanicaController {

    @Autowired
    private StanicaService stanicaService;

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private RutaService rutaService;

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(StanicaDTO update_stanica, @RequestParam long rutaId, HttpServletResponse response) {
        Stanica stanica = new Stanica();
        try {
            stanica.setNaziv(update_stanica.getNaziv());
            stanica.setLokacija(update_stanica.getLokacija());
            stanica.setZona(zonaService.findById(Long.valueOf(update_stanica.getZonaId())).get());
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (!update_stanica.getId().isEmpty()) {
            stanica.setId(Long.valueOf(update_stanica.getId()));
            stanicaService.update(stanica);
        } else {
            stanicaService.create(stanica);
            Ruta ruta = rutaService.findById(rutaId).get();
            ruta.addStanica(stanica);
            rutaService.update(ruta);
        }
        try {
            response.sendRedirect("/view/" + rutaId);
        } catch (IOException e) {
            return e.getMessage();
        }
        return "";
    }

    @PostMapping(value = "/request", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String request(StanicaDTO update_stanica, @RequestParam long rutaId, HttpServletResponse response) {
        Stanica stanica = new Stanica();
        try {
            stanica.setNaziv(update_stanica.getNaziv());
            stanica.setLokacija(update_stanica.getLokacija());
            stanica.setZona(zonaService.findById(Long.valueOf(update_stanica.getZonaId())).get());
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        if (!update_stanica.getId().isEmpty()) {
            return "Not yet supported";
        } else {
            stanicaService.create(stanica);
            Ruta ruta = rutaService.findById(rutaId).get();
            ruta.addZahtjevanaStanica(stanica);
            rutaService.update(ruta);
        }
        try {
            response.sendRedirect("/view/" + rutaId);
        } catch (IOException e) {
            return e.getMessage();
        }
        return "";
    }

}
