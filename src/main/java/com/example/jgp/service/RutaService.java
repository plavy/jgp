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

    public Ruta findByName(String name) {
        return repo.findByNaziv(name);
    }

    public Ruta create(Ruta ruta) {
        return repo.save(ruta);
    }

    public Ruta update(Ruta update_ruta) {
        Ruta ruta = findById(update_ruta.getId()).get();
        ruta.setNaziv(update_ruta.getNaziv());
        ruta.setAdmin(update_ruta.getAdmin());
        return repo.save(ruta);
    }

    public Ruta updateStanice(Ruta update_ruta) {
        Ruta ruta = findById(update_ruta.getId()).get();
        ruta.setStanice(update_ruta.getStanice());
        ruta.setZahtjevaneStanice(update_ruta.getZahtjevaneStanice());
        return repo.save(ruta);
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }

}
