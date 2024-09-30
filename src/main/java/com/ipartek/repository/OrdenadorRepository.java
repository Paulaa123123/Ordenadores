package com.ipartek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Ordenador;

@Repository
public interface OrdenadorRepository extends JpaRepository<Ordenador, Integer> {

}
