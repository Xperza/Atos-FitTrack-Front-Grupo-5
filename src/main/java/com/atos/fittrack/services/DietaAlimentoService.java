package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Alimento;
import com.atos.fittrack.entities.Dieta;
import com.atos.fittrack.entities.Dietaalimento;
import com.atos.fittrack.repository.DietaAlimentoRepository;

@Service
public class DietaAlimentoService implements ICRUD<Dietaalimento, Integer> {

	@Autowired
	DietaAlimentoRepository dietaAlimentoDao;
	
	@Override
	public List<Dietaalimento> findAll() {
		return dietaAlimentoDao.findAll();
	}

	public void deleteByDietaAlimento(Dietaalimento dietaAlimento) {
		dietaAlimentoDao.delete(dietaAlimento);
	}

	@Override
	public Dietaalimento save(Dietaalimento entity) {
		return dietaAlimentoDao.save(entity);
	}
	
	public List<Dietaalimento> findAllByDieta(Dieta dieta){
		return dietaAlimentoDao.findAllByDieta(dieta);
	}

	public List<Dietaalimento> findAllByAlimento(Alimento alimento){
		return dietaAlimentoDao.findAllByAlimento(alimento);
	}
	
	@Override
	public Optional<Dietaalimento> findById(Integer id) {
		return dietaAlimentoDao.findById(id);
	}
	
	@Override
	public void delete(Integer id) {
		dietaAlimentoDao.deleteById(id);
	}
}
 