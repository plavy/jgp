package com.example.jgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jgp.model.Zona;
import com.example.jgp.repository.ZonaRepository;

@Service
public class ZonaService {
    
    @Autowired
    ZonaRepository repo;

    public List<Zona> listAll() {
        return repo.findAll();
    }

    public Optional<Zona> findById(Long id) {
        return repo.findById(id);
    }

    public Zona create(Zona zona) {
        return repo.save(zona);
    }
}
