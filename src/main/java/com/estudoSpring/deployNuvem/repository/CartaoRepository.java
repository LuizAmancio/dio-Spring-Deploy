package com.estudoSpring.deployNuvem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudoSpring.deployNuvem.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

}
