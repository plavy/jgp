package com.example.jgp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jgp.service.StanicaService;


@RestController
@RequestMapping("/stanica")
public class StanicaController {
    
    @Autowired
    StanicaService stanicaService;

}
