package com.example.jgp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jgp.model.Ruta;
import com.example.jgp.service.RutaService;

@Controller
@RequestMapping("/rute")
public class MasterController {
    @Autowired
    private RutaService rutaService;

    @GetMapping("")
    public String getView(Model model, @RequestParam(required = false) String term) {
        List<Ruta> rute = rutaService.listAll();
        if (term == null || term.isEmpty()) {
            model.addAttribute("rute", rute);
            model.addAttribute("term", "");
        } else {
            rute = rute.stream()
                    .filter((x) -> x.getNaziv().toLowerCase().contains(term.toLowerCase())
                            || x.getAdmin().getNaziv().toLowerCase().contains(term.toLowerCase()))
                    .collect(Collectors.toList());
            model.addAttribute("rute", rute);
            model.addAttribute("term", term);
        }
        return "master";
    }
}
