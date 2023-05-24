package com.example.jgp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jgp.dto.RutaDTO;
import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;
import com.example.jgp.service.StanicaService;
import com.example.jgp.service.ZonaService;

@Controller
@RequestMapping("/")
public class MasterDetailController {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StanicaService stanicaService;

    @Autowired
    private ZonaService zonaService;

    @GetMapping("")
    public String redirectToRuta() {
        List<Ruta> rute = rutaService.listAll();
        if (rute.size() > 0) {
            long id = rutaService.listAll().get(0).getId();
            return "redirect:/view/" + id;
        } else {
            return "redirect:/rute";
        }
    }

    @GetMapping("/view/{rutaId}")
    public String getView(Model model, @PathVariable long rutaId) {
        Ruta ruta = rutaService.findById(rutaId).get();
        List<Ruta> listRuta = rutaService.listAll();
        int index = listRuta.indexOf(ruta);
        Long prev_id = listRuta.get((index - 1 + listRuta.size()) % listRuta.size()).getId();
        Long next_id = listRuta.get((index + 1 + listRuta.size()) % listRuta.size()).getId();
        model.addAttribute("prev_id", prev_id.toString());
        model.addAttribute("next_id", next_id.toString());
        model.addAttribute("ruta", ruta);
        model.addAttribute("stanice", ruta.getStanice());
        model.addAttribute("admins", adminService.listAll());
        model.addAttribute("update_ruta", new RutaDTO());
        model.addAttribute("sve_stanice", stanicaService.listAll());
        return "master-detail";
    }

    @PostMapping("/edit/{stanicaId}")
    public String getEditView(Model model, @PathVariable long stanicaId, @RequestParam long rutaId) {
        model.addAttribute("rutaId", rutaId);
        model.addAttribute("stanica", stanicaService.findById(stanicaId).get());
        model.addAttribute("zone", zonaService.listAll());
        return "stanica";
    }

    @PostMapping("/edit")
    public String getEditView(Model model, @RequestParam long rutaId) {
        Stanica stanica = new Stanica();
        stanica.setNaziv("Nova");
        stanica.setLokacija("Ulica 1");
        stanica.setZona(zonaService.listAll().get(0));
        model.addAttribute("rutaId", rutaId);
        model.addAttribute("stanica", stanica);
        model.addAttribute("zone", zonaService.listAll());
        return "stanica";
    }

}
