package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.EjercicioVr;
import com.atos.fittrack.entities.Objetivo;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.repository.EjercicioVrRepository;

@Service
public class EjercicioVrService implements ICRUD<EjercicioVr, Integer> {

	@Autowired
	private EjercicioVrRepository ejercicioVrDao;
	
	@Override
	public List<EjercicioVr> findAll() {
		return ejercicioVrDao.findAll();
	}

	@Override
	public void delete(Integer id) {
		ejercicioVrDao.deleteById(id);
	}

	@Override
	public Optional<EjercicioVr> findById(Integer id) {
		return ejercicioVrDao.findById(id);
	}

	@Override
	public EjercicioVr save(EjercicioVr entity) {
		return ejercicioVrDao.save(entity);
	}
	
	public List<EjercicioVr> findByUsuario(Usuario usuario){
		return ejercicioVrDao.findByUsuario(usuario);
	}
	public List<EjercicioVr> findEjercicio_VrMensual(int mes, int year,int id){
		return ejercicioVrDao.findEjercicio_VRByMonth(mes, year, id);
	}
	
	public List<EjercicioVr> findEjercicio_VrAnual(int year,int id){
		return ejercicioVrDao.findEjercicio_VRByYear(year, id);
	}

}
