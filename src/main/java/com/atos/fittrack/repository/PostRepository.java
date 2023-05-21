package com.atos.fittrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Post;
import com.atos.fittrack.entities.Usuario;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

	Optional<Post> findByUsuario(Usuario usuario);
}
