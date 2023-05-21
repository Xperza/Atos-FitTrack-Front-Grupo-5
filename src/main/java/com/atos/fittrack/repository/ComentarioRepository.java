package com.atos.fittrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Comentario;
import com.atos.fittrack.entities.Ejercicio;
import com.atos.fittrack.entities.Usuario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
	List<Comentario> findAllById(Integer id);
}
