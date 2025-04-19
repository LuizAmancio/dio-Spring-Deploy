package com.estudoSpring.deployNuvem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudoSpring.deployNuvem.model.BaseItem;

@Repository
public interface BaseItemRepository extends JpaRepository<BaseItem, Long> {

}
