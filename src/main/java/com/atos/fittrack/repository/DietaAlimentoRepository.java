package com.atos.fittrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atos.fittrack.entities.Alimento;
import com.atos.fittrack.entities.Dieta;
import com.atos.fittrack.entities.Dietaalimento;

@Repository
public interface DietaAlimentoRepository extends JpaRepository<Dietaalimento, Integer> {
	List<Dietaalimento> findAllByDieta(Dieta dieta);
	List<Dietaalimento> findAllByAlimento(Alimento alimento);
}
