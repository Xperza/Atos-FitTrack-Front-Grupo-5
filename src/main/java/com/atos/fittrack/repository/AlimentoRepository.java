package com.atos.fittrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Alimento;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {

}
