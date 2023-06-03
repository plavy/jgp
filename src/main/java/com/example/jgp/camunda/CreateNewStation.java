package com.example.jgp.camunda;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.jgp.model.Zona;
import com.example.jgp.service.ZonaService;

@Named
public class CreateNewStation implements JavaDelegate {

    @Autowired
    ZonaService zonaService;

    @Override
    public void execute(DelegateExecution arg0) throws Exception {
        Zona zona = new Zona();
        zona.setNaziv("Autozona");
        zonaService.create(zona);
        
    }
}
