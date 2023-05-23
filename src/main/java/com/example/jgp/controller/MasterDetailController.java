package com.example.jgp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jgp.dto.RutaDTO;
import com.example.jgp.model.Ruta;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;

@Controller
@RequestMapping("/")
public class MasterDetailController {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String redirectToRuta() {
        long id = rutaService.listAll().get(0).getId();
        return "redirect:/" + id;
    }

    @GetMapping("/{rutaId}")
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
        return "master-detail";
    }
}