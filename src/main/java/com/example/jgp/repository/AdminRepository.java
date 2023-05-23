package com.example.jgp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jgp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    
}
