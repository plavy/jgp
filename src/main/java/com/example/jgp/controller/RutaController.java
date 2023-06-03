package com.example.jgp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jgp.dto.RutaDTO;
import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StanicaService stanicaService;

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String update(RutaDTO update_ruta, HttpServletResponse response) {
        if (update_ruta.getNaziv().length() < 1 ) {
            return "ERROR Naziv mora imati barem 1 znak.";
        }
        Ruta ruta = new Ruta();
        ruta.setId(Long.valueOf(update_ruta.getId()));
        ruta.setNaziv(update_ruta.getNaziv());
        ruta.setAdmin(adminService.findById(Long.valueOf(update_ruta.getAdminId())).get());
        rutaService.update(ruta);
        try {
            response.sendRedirect("/view/" + ruta.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @PostMapping("/delete/{rutaId}")
    public void delete(@PathVariable long rutaId, HttpServletResponse response) {
        rutaService.deleteById(rutaId);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/remove/{rutaId}")
    public void removeStanica(@PathVariable long rutaId, @RequestParam long stanicaId, HttpServletResponse response) {
        Ruta ruta = rutaService.findById(rutaId).get();
        Stanica stanica = stanicaService.findById(stanicaId).get();
        ruta.removeStanica(stanica);
        rutaService.updateStanice(ruta);
        try {
            response.sendRedirect("/view/" + ruta.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/add/{rutaId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addStanica(long stanicaId, @PathVariable long rutaId, HttpServletResponse response){
        Ruta ruta = rutaService.findById(rutaId).get();
        Stanica stanica = stanicaService.findById(stanicaId).get();
        if (ruta.getStanice().contains(stanica)) {
            return "ERROR Stanica je već u ruti.";
        }
        ruta.addStanica(stanica);
        rutaService.updateStanice(ruta);
        try {
            response.sendRedirect("/view/" + ruta.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
