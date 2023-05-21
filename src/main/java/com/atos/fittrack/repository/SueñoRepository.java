package com.atos.fittrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Sueño;
import com.atos.fittrack.entities.Usuario;

@Repository
public interface SueñoRepository extends JpaRepository<Sueño, Integer> {
	Optional<Sueño> findByUsuario(Usuario usuario);
}
