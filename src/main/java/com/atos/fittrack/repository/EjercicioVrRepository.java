package com.atos.fittrack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.EjercicioVr;
import com.atos.fittrack.entities.Objetivo;
import com.atos.fittrack.entities.Sueño;
import com.atos.fittrack.entities.Usuario;

@Repository
public interface EjercicioVrRepository extends JpaRepository<EjercicioVr, Integer> {
	List<EjercicioVr> findByUsuario(Usuario usuario);
	
	@Query(value="SELECT * FROM Ejercicio_VR WHERE (MONTH(fecha) = :mes AND YEAR(fecha) = :year AND id_usuario=:id);",nativeQuery=true)
	List<EjercicioVr> findEjercicio_VRByMonth(int mes, int year,int id);
	
	@Query(value="SELECT * FROM Ejercicio_VR WHERE (YEAR(fecha) = :year AND id_usuario=:id);",nativeQuery=true)
	List<EjercicioVr> findEjercicio_VRByYear(int year, int id);
}
