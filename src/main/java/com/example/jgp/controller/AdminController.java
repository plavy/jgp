package com.example.jgp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jgp.model.Admin;
import com.example.jgp.model.Ruta;
import com.example.jgp.model.Stanica;
import com.example.jgp.service.AdminService;
import com.example.jgp.service.RutaService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    AdminService adminService;

    @Autowired
    RutaService rutaService;

    @GetMapping("")
    public String redirectToRuta() {
        List<Admin> admins = adminService.listAll();
        if (admins.size() > 0) {
            long id = adminService.listAll().get(0).getId();
            return "redirect:/admin/view/" + id;
        } else {
            return "redirect:/administratori";
        }
    }

    @GetMapping("/view/{adminId}")
    public String getView(Model model, @PathVariable long adminId) {
        Admin admin = adminService.findById(adminId).get();
        List<Stanica> zahtjevaneStanice = new ArrayList<>();
        Map<Long, Ruta> mapa = new HashMap<>();
        for(Ruta ruta : rutaService.listAll()) {
            if (ruta.getAdmin() != admin) {
                continue;
            }
            List<Stanica> zStanice = ruta.getZahtjevaneStanice();
            zahtjevaneStanice.addAll(zStanice);
            for (Stanica s : zStanice) {
                mapa.put(s.getId(), ruta);
            }
        }
        model.addAttribute("admin", admin);
        model.addAttribute("stanice", zahtjevaneStanice);
        model.addAttribute("mapa", mapa);
        return "admin-view";
    }
}
