package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.DietaAlimentoDTO;
import com.atos.fittrack.entities.Dietaalimento;
@Component
public class DietaAlimentoMapper {

	@Autowired
	private AlimentoMapper alimentoMapper;
	
	@Autowired
	private DietaMapper dietaMapper;
	
	public DietaAlimentoDTO toDietaAlimentoDTO(Dietaalimento dietaAlimento) {
		DietaAlimentoDTO dto = new DietaAlimentoDTO();

		dto.setId(dietaAlimento.getId());
		dto.setAlimento(alimentoMapper.toAlimentoDTO(dietaAlimento.getAlimento()));
		dto.setDieta(dietaMapper.toDietaDTO(dietaAlimento.getDieta()));
		dto.setTipo(dietaAlimento.getTipo());
		
		return dto;
	}
	
	public Dietaalimento toEntity(DietaAlimentoDTO dto) {
		Dietaalimento dietaAlimento = new Dietaalimento();
		
		dietaAlimento.setId(dto.getId());
		dietaAlimento.setAlimento(alimentoMapper.toEntity(dto.getAlimento()));
		dietaAlimento.setDieta(dietaMapper.toEntity(dto.getDieta()));
		dietaAlimento.setTipo(dto.getTipo());
		
		return dietaAlimento;
	}
	
	public List<DietaAlimentoDTO> changeListToDTO(List<Dietaalimento> lista) {
		List<DietaAlimentoDTO> newListaDtos = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Dietaalimento dietaAlimento = lista.get(i);
			newListaDtos.add(toDietaAlimentoDTO(dietaAlimento));
		}
		return newListaDtos;
	}
}
