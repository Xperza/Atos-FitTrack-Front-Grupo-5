package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.Ejercicio;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.repository.EjercicioRepository;

@Service
public class EjercicioService implements ICRUD<Ejercicio, Integer> {

	@Autowired
	private EjercicioRepository ejercicioDao;
	
	@Override
	public List<Ejercicio> findAll() {
		return (List<Ejercicio>) ejercicioDao.findAll();
	}

	@Override
	public Ejercicio save(Ejercicio ejercicio) {
		return ejercicioDao.save(ejercicio);
	}

	@Override
	public Optional<Ejercicio> findById(Integer id) {
		return ejercicioDao.findById(id);
	}
	

	@Override
	public void delete(Integer id) {
		ejercicioDao.deleteById(id);
	}
	
	public List<Ejercicio> findByUsuario(Usuario usuario) {
		return ejercicioDao.findByUsuario(usuario);
	}
}
