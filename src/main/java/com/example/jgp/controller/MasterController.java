package com.example.jgp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jgp.service.RutaService;

@Controller
@RequestMapping("/rute")
public class MasterController {
    @Autowired
    private RutaService rutaService;

    @GetMapping("")
    public String getView(Model model) {
        model.addAttribute("rute", rutaService.listAll());
        return "master";
    }
}
