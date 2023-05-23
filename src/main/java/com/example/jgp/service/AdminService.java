package com.example.jgp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jgp.model.Admin;
import com.example.jgp.repository.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    AdminRepository repo;

    public List<Admin> listAll() {
        return repo.findAll();
    }

    public Optional<Admin> findById(Long id) {
        return repo.findById(id);
    }

    public Admin create(Admin admin) {
        return repo.save(admin);
    }
}