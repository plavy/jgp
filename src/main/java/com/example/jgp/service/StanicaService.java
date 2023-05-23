package com.example.jgp.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Stanica> findById(Long id) {
        return repo.findById(id);
    }

    public Stanica create(Stanica stanica) {
        return repo.save(stanica);
    }

    public Stanica update(Stanica update_stanica) {
        Stanica stanica = findById(update_stanica.getId()).get();
        stanica.setNaziv(update_stanica.getNaziv());
        stanica.setLokacija(update_stanica.getLokacija());
        stanica.setZona(update_stanica.getZona());
        return repo.save(stanica);
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }

}
