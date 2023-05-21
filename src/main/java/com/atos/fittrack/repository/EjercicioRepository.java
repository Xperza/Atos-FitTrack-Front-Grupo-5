package com.atos.fittrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Ejercicio;
import com.atos.fittrack.entities.Usuario;

@Repository
public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {
	List<Ejercicio> findByUsuario(Usuario usuario);
}
