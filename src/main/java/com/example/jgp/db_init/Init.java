package com.example.jgp.db_init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.jgp.model.Admin;
import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.model.Zona;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

@Component
public class Init {
    
    @Autowired
    ZonaService zonaService;

    @Autowired
    StanicaService stanicaService;

    @Autowired
    AdminService adminService;

    @Autowired
    RutaService rutaService;

    @EventListener(ApplicationReadyEvent.class)
    public void doAfterStart() {
        if(stanicaService.listAll().size() == 0){

            Zona zona1 = new Zona();
            zona1.setNaziv("1");
            zonaService.create(zona1);

            Zona zona2 = new Zona();
            zona2.setNaziv("2");
            zonaService.create(zona2);

            Stanica stanica1 = new Stanica();
            stanica1.setNaziv("Vodnikova");
            stanica1.setLokacija("Vodnikova ulica 24");
            stanica1.setZona(zona1);
            stanicaService.create(stanica1);

            Stanica stanica2 = new Stanica();
            stanica2.setNaziv("Trg");
            stanica2.setLokacija("Trg bana Josipa Jelačića 2");
            stanica2.setZona(zona1);
            stanicaService.create(stanica2);

            Stanica stanica3 = new Stanica();
            stanica3.setNaziv("Vončinina");
            stanica3.setLokacija("Vlaška ulica 73");
            stanica3.setZona(zona1);
            stanicaService.create(stanica3);
            
            Stanica stanica4 = new Stanica();
            stanica4.setNaziv("Autobusni kolodvor");
            stanica4.setLokacija("Avenija Marina Držića 4");
            stanica4.setZona(zona2);
            stanicaService.create(stanica4);

            Admin admin1 = new Admin();
            admin1.setNaziv("Petar Poljak");
            admin1.setMail("ppoljak@admin.com");
            adminService.create(admin1);

            Admin admin2 = new Admin();
            admin2.setNaziv("Nikola Kop");
            admin2.setMail("nkop@admin.com");
            adminService.create(admin2);

            Ruta ruta1 = new Ruta();
            ruta1.setNaziv("1");
            ruta1.setStanice(List.of(stanica1, stanica2, stanica3));
            ruta1.setAdmin(admin1);
            rutaService.create(ruta1);

            Ruta ruta2 = new Ruta();
            ruta2.setNaziv("2");
            ruta2.setStanice(List.of(stanica3, stanica4));
            ruta2.setAdmin(admin1);
            rutaService.create(ruta2);
        }

    }
}
