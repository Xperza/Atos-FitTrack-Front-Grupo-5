package com.atos.fittrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Calendario;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Integer>{

}
