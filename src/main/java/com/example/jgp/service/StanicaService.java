package com.example.jgp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jgp.model.Stanica;
import com.example.jgp.repository.StanicaRepository;

@Service
public class StanicaService {
    
    @Autowired
    private StanicaRepository repo;

    public List<Stanica> listAll() {
        return repo.findAll();
    }

    public Stanica create(Stanica stanica) {
        return repo.save(stanica);
    }
}
