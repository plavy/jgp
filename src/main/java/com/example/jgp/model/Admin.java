package com.example.jgp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Administratori")
public class Admin {
    
    @Id
    @GeneratedValue
    @Column(name = "adminId")
    private Long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String mail;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return naziv;
    }

}
