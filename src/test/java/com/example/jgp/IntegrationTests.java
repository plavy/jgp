package com.example.jgp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jgp.model.Admin;
import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.model.Zona;
import com.example.jgp.repository.RutaRepository;
import com.example.jgp.repository.StanicaRepository;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class IntegrationTests {

    @Autowired
    ZonaService zonaService;

    @Autowired
    StanicaRepository stanicaRepository;

    @Autowired
    StanicaService stanicaService;

    @Autowired
    AdminService adminService;

    @Autowired
    RutaRepository rutaRepository;

    @Autowired
    RutaService rutaService;

    @Test
    public void integrationTest1() {
        Zona zona = new Zona();
        zona.setNaziv("Test zona");
        zonaService.create(zona);

        Stanica stanica = new Stanica();
        stanica.setNaziv("Dubec");
        stanica.setLokacija("Dubečka 1");
        stanica.setZona(zona);
        stanicaService.create(stanica);

        boolean is_present = stanicaRepository.findById(stanica.getId()).isPresent();

        stanicaService.deleteById(stanica.getId());
        zonaService.deleteById(zona.getId());

        assertThat(is_present).isEqualTo(true);
    }

    @Test
    public void integrationTest2() {
        Admin admin = new Admin();
        admin.setNaziv("Test Probić");
        admin.setMail("test@admin.com");
        adminService.create(admin);

        Ruta ruta = new Ruta();
        ruta.setNaziv("TEST");
        ruta.setAdmin(admin);
        rutaService.create(ruta);

        boolean is_present = rutaRepository.findById(ruta.getId()).isPresent();
        
        rutaService.deleteById(ruta.getId());
        adminService.deleteById(admin.getId());

        assertThat(is_present).isEqualTo(true);
    }
}
