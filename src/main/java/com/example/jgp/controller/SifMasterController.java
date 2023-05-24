package com.example.jgp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.jgp.service.AdminService;

@Controller
@RequestMapping("/administratori")
public class SifMasterController {
    
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String getView(Model model) {
        model.addAttribute("admins", adminService.listAll());
        return "sif-master";
    }
}
