package com.example.jgp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "RUTE")
public class Ruta {

    @Id
    @GeneratedValue
    @Column(name = "rutaId")
    private Long id;

    @Column(nullable = false, unique = true)
    private String naziv;

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
        if (naziv.length() < 1) {
            throw new IllegalArgumentException("Naziv rute mora imati barem 1 znak");
        }
        this.naziv = naziv;
    }

    public List<Stanica> getStanice() {
        return stanice;
    }

    public void setStanice(List<Stanica> stanice) {
        this.stanice = stanice;
    }

    public void removeStanica(Stanica stanica) {
        this.stanice.remove(stanica);
    }

    public void addStanica(Stanica stanica) {
        if (this.stanice.contains(stanica)) {
            throw new IllegalArgumentException("Stanica je već u ruti.");
        }
        this.stanice.add(stanica);
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

}
