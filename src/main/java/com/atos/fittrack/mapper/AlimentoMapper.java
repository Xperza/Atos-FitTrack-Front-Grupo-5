package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.AlimentoDTO;
import com.atos.fittrack.entities.Alimento;

@Component
public class AlimentoMapper {

	public AlimentoDTO toAlimentoDTO(Alimento alimento) {
		AlimentoDTO dto = new AlimentoDTO();

		dto.setId(alimento.getId());
		dto.setNombre(alimento.getNombre());
		dto.setAlergenos(alimento.getAlergenos());
		dto.setIngredientes(alimento.getIngredientes());
		dto.setCalorias(alimento.getCalorias());
		dto.setValorNutricional(alimento.getValorNutricional());
		return dto;
	}

	public Alimento toEntity(AlimentoDTO dto) {
		
		Alimento alimento = new Alimento();
		alimento.setAlergenos(dto.getAlergenos());
		alimento.setCalorias(dto.getCalorias());
		alimento.setId(dto.getId());
		alimento.setIngredientes(dto.getIngredientes());
		alimento.setNombre(dto.getNombre());
		alimento.setValorNutricional(dto.getValorNutricional());
		return alimento;
	}

	public List<AlimentoDTO> changeListToDTO(List<Alimento> lista) {
		List<AlimentoDTO> newListaDtos = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Alimento alimento = lista.get(i);
			newListaDtos.add(toAlimentoDTO(alimento));
		}
		return newListaDtos;
	}

}
