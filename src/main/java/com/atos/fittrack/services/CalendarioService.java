package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.fittrack.entities.Calendario;
import com.atos.fittrack.repository.CalendarioRepository;

@Service
public class CalendarioService implements ICRUD<Calendario, Integer> {

	@Autowired
	private CalendarioRepository calendarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Calendario> findAll() {
		return (List<Calendario>) calendarioDao.findAll();
	}

	@Override
	@Transactional()
	public void delete(Integer id) {
		calendarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Calendario> findById(Integer id) {
		return calendarioDao.findById(id);
	}

	@Override
	@Transactional()
	public Calendario save(Calendario entity) {
		return calendarioDao.save(entity);
	}

}
