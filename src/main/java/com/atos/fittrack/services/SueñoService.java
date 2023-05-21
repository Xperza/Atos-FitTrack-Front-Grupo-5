package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.fittrack.entities.Sueño;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.repository.SueñoRepository;

@Service
public class SueñoService implements ICRUD<Sueño, Integer> {

	@Autowired
	private SueñoRepository suenioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Sueño> findAll() {
		return (List<Sueño>) suenioDao.findAll();
	}

	@Override
	@Transactional()
	public void delete(Integer id) {
		suenioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Sueño> findById(Integer id) {
		return suenioDao.findById(id);
	}
	
	@Transactional(readOnly = true)
	public Optional<Sueño> findByUsuario(Usuario usuario) {
		return suenioDao.findByUsuario(usuario);
	}

	@Override
	@Transactional()
	public Sueño save(Sueño entity) {
		return suenioDao.save(entity);
	}

}
