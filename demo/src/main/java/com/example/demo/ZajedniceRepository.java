package com.example.demo;

import com.example.demo.model.Zajednice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ZajedniceRepository extends JpaRepository<Zajednice, Integer>, JpaSpecificationExecutor<Zajednice> {

}