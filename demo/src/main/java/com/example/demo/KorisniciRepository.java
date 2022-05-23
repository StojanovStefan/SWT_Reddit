package com.example.demo;

import com.example.demo.model.Korisnici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KorisniciRepository extends JpaRepository<Korisnici, Integer>, JpaSpecificationExecutor<Korisnici> {

}