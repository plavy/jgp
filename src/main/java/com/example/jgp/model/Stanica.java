package com.example.jgp.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "STANICE")
public class Stanica {

    @Id
    @GeneratedValue
    @Column(name = "stanicaId")
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String lokacija;

    @ManyToOne
    private Zona zona;

    @ManyToMany(mappedBy = "stanice", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private Set<Ruta> rute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    public Set<Ruta> getRute() {
        return rute;
    }

    public void setRute(Set<Ruta> rute) {
        this.rute = rute;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

}
