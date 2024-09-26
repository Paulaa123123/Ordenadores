package com.ipartek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
