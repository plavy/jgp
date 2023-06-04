package com.example.jgp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
        if (naziv.length() < 1) {
            throw new IllegalArgumentException("Naziv stanice mora imati barem 1 znak.");
        }
        this.naziv = naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        if (lokacija.length() < 1) {
            throw new IllegalArgumentException("Lokacija stanice mora imati barem 1 znak.");
        }
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
