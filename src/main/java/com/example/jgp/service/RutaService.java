package com.example.jgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jgp.model.Ruta;
import com.example.jgp.repository.RutaRepository;

@Service
public class RutaService {
    
    @Autowired
    RutaRepository repo;

    public List<Ruta> listAll() {
        return repo.findAll();
    }

    public Optional<Ruta> findById(Long id) {
        return repo.findById(id);
    }

    public Ruta create(Ruta ruta) {
        return repo.save(ruta);
    }
}
