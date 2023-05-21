package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Comentario;
import com.atos.fittrack.repository.ComentarioRepository;

@Service
public class ComentarioService implements ICRUD<Comentario, Integer> {

	@Autowired
	ComentarioRepository comentarioDao;
	
	@Override
	public List<Comentario> findAll() {
		return (List<Comentario>) comentarioDao.findAll();
	}

	@Override
	public void delete(Integer id) {
		comentarioDao.deleteById(id);
	}

	@Override
	public Optional<Comentario> findById(Integer id) {
		return comentarioDao.findById(id);
	}

	@Override
	public Comentario save(Comentario entity) {
		return comentarioDao.save(entity);
	}
 
	
	public List<Comentario> findAllById(Integer id) {
		return comentarioDao.findAllById(id);
	}
	
}
