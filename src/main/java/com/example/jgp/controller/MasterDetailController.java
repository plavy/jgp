package com.example.jgp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getView(Model model) {
        model.addAttribute("stanice", rutaService.findById(1l).get().getStanice());
        model.addAttribute("current_admin", rutaService.findById(1l).get().getAdmin());
        model.addAttribute("admins", adminService.listAll());
        return "master-detail";
    }
}