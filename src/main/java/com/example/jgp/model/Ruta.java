package com.example.jgp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "RUTE")
public class Ruta {

    @Id
    @GeneratedValue
    @Column(name = "rutaId")
    private Long id;

    private String naziv;

    @ManyToOne
    private Stanica smjer;

    @ManyToOne
    private Admin admin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE })
    @JoinTable(joinColumns = @JoinColumn(name = "rutaId"), inverseJoinColumns = @JoinColumn(name = "stanicaId"))
    @OrderColumn
    private List<Stanica> stanice;

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

    public Stanica getSmjer() {
        return smjer;
    }

    public void setSmjer(Stanica smjer) {
        this.smjer = smjer;
    }

    public List<Stanica> getStanice() {
        return stanice;
    }

    public void setStanice(List<Stanica> stanice) {
        this.stanice = stanice;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
