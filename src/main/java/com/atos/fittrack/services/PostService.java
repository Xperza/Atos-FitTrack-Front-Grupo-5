package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Post;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.repository.PostRepository;

@Service
public class PostService implements ICRUD<Post,Integer>{
	
	@Autowired
	private PostRepository repository;

	@Override
	public List<Post> findAll() {
		return (List<Post>) repository.findAll();
	}

	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
			
	}

	@Override
	public Optional<Post> findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Post save(Post entity) {
		return repository.save(entity);
	}
	
	public Optional<Post> findByUsuario(Usuario usuario){
		return repository.findByUsuario(usuario);
	}

}
