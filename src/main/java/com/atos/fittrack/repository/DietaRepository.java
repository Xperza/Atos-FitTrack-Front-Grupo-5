package com.atos.fittrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Dieta;

@Repository
public interface DietaRepository extends JpaRepository<Dieta, Integer>{

}
