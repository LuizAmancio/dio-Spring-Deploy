package com.estudoSpring.deployNuvem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudoSpring.deployNuvem.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

}
