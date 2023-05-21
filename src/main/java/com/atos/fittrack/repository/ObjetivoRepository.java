package com.atos.fittrack.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Objetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {
	

	@Query(value="SELECT * FROM Objetivo WHERE (MONTH(fecha_inicio) = :mes AND YEAR(fecha_inicio) = :year AND id_usuario=:id);",nativeQuery=true)
	List<Objetivo> findObjetivoByMonth(int mes, int year,int id);
	
	@Query(value="SELECT * FROM Objetivo WHERE (YEAR(fecha_inicio) = :year AND id_usuario=:id);",nativeQuery=true)
	List<Objetivo> findObjetivoByYear(int year, int id);

}