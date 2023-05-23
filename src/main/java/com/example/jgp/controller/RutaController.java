package com.example.jgp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jgp.dto.RutaDTO;
import com.example.jgp.model.Ruta;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    RutaService rutaService;

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void update(RutaDTO update_ruta, HttpServletResponse response) {
        Ruta ruta = new Ruta();
        ruta.setId(Long.valueOf(update_ruta.getId()));
        ruta.setNaziv(update_ruta.getNaziv());
        ruta.setAdmin(adminService.findById(Long.valueOf(update_ruta.getAdminId())).get());
        rutaService.update(ruta);
        try {
            response.sendRedirect("/" + ruta.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
