package com.example.jgp.camunda;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;

@Named
public class RemoveStationRequest implements JavaDelegate {

    @Autowired
    RutaService rutaService;

    @Autowired
    StanicaService stanicaService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Ruta ruta = rutaService.findByName(execution.getVariable("ruta").toString());
        Stanica stanica = stanicaService.findById(Long.valueOf(execution.getVariable("id").toString())).get();
        ruta.removeZahtjevanaStanica(stanica);
        rutaService.updateStanice(ruta);
    }
}
