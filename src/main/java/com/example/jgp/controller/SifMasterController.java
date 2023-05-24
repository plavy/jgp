package com.example.jgp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jgp.model.Admin;
import com.example.jgp.service.AdminService;

@Controller
@RequestMapping("/administratori")
public class SifMasterController {

    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String getView(Model model, @RequestParam(required = false) String term) {
        List<Admin> admins = adminService.listAll();
        if (term == null || term.isEmpty()) {
            model.addAttribute("admins", admins);
            model.addAttribute("term", "");
        } else {
            admins = admins.stream().filter((x) -> x.getNaziv().toLowerCase().contains(term.toLowerCase())
                    || x.getMail().toLowerCase().contains(term.toLowerCase())).collect(Collectors.toList());
            model.addAttribute("admins", admins);
            model.addAttribute("term", term);
        }
        return "sif-master";
    }
}
