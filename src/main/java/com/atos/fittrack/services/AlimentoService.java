package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.fittrack.entities.Alimento;
import com.atos.fittrack.repository.AlimentoRepository;

@Service
public class AlimentoService implements ICRUD<Alimento,Integer> {

	@Autowired
	private AlimentoRepository alimentoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Alimento> findAll() {
		return (List<Alimento>) alimentoDao.findAll();
	}

	@Override
	@Transactional()
	public Alimento save(Alimento alimento) {
		return alimentoDao.save(alimento);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Alimento> findById(Integer id) {
		return alimentoDao.findById(id);
	}

	@Override
	@Transactional()
	public void delete(Integer id) {
		alimentoDao.deleteById(id);
	}
}
