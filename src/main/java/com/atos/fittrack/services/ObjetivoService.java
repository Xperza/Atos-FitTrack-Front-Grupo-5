package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atos.fittrack.entities.Objetivo;
import com.atos.fittrack.repository.ObjetivoRepository;

@Service
public class ObjetivoService implements ICRUD<Objetivo,Integer>{

	@Autowired
	private ObjetivoRepository objetivoDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Objetivo> findAll() {
		return (List<Objetivo>) objetivoDao.findAll();
	}
	

	@Override
	@Transactional()
	public Objetivo save(Objetivo objetivo) {
		return objetivoDao.save(objetivo);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Objetivo> findById(Integer id) {
		return objetivoDao.findById(id);
	}

	@Override
	@Transactional()
	public void delete(Integer id) {
		objetivoDao.deleteById(id);
	}

	public List<Objetivo> getIntervaloMes(Integer mes, Integer year,Integer id){
		return objetivoDao.findObjetivoByMonth(mes,year,id);
	}
	
	public List<Objetivo> getIntervaloYear(Integer year,Integer id){
		return objetivoDao.findObjetivoByYear(year,id);
	}
}
