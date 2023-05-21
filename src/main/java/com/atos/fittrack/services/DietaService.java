package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Dieta;
import com.atos.fittrack.repository.DietaRepository;

@Service
public class DietaService implements ICRUD<Dieta, Integer> {

	@Autowired
	private DietaRepository dietaDao;
	
	@Override
	public List<Dieta> findAll() {
		return dietaDao.findAll();
	}

	@Override
	public void delete(Integer id) {
		dietaDao.deleteById(id);
	}

	@Override
	public Optional<Dieta> findById(Integer id) {
		return dietaDao.findById(id);
	}

	@Override
	public Dieta save(Dieta entity) {
		return dietaDao.save(entity);
	}

}
