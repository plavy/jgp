package com.example.jgp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jgp.model.Stanica;
import com.example.jgp.model.Zona;
import com.example.jgp.repository.StanicaRepository;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegrationTest1 {
    
    @Autowired
    ZonaService zonaService;

    @Autowired
    StanicaRepository stanicaRepository;

    @Autowired
    StanicaService stanicaService;

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

        assertThat(stanicaRepository.findById(stanica.getId() +1 )).isNotNull();
    }
}
