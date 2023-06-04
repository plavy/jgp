package com.example.jgp.camunda;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

@Named
public class CreateStationRequest implements JavaDelegate {

    @Autowired
    ZonaService zonaService;

    @Autowired
    StanicaService stanicaService;

    @Autowired
    RutaService rutaService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Stanica stanica = new Stanica();
        try {
            stanica.setNaziv(execution.getVariable("naziv").toString());
            stanica.setLokacija(execution.getVariable("lokacija").toString());
            stanica.setZona(zonaService.findByName(execution.getVariable("zona").toString()));

            stanicaService.create(stanica);
            execution.setVariable("id", stanica.getId());

            Ruta ruta = rutaService.findByName(execution.getVariable("ruta").toString());
            ruta.addZahtjevanaStanica(stanica);
            rutaService.update(ruta);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
