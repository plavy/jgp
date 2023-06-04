package com.example.jgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jgp.model.Ruta;

public interface RutaRepository extends JpaRepository<Ruta, Long>{
    public Ruta findByNaziv(String naziv);
}
