package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.DietaDTO;
import com.atos.fittrack.entities.Dieta;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.services.UsuarioService;

@Component
public class DietaMapper {

	@Autowired
	UsuarioMapper usuarioMapper;
	
	@Autowired
	AlimentoMapper alimentoMapper;
	
	@Autowired
	UsuarioService usuario;
	
	public int getIdUsuario(Dieta dieta) {
		return usuario.findById(dieta.getUsuario().getId()).get().getId();
	}
	public Usuario getUsuarioDTO(DietaDTO dieta) {
		return usuario.findById(dieta.getUsuario()).get();
	}
	public DietaDTO toDietaDTO(Dieta dieta) {
		DietaDTO dto = new DietaDTO();
		
		dto.setNombre(dieta.getNombre());
		dto.setCheatDay(dieta.getCheatDay());
		dto.setId(dieta.getId());
		dto.setObservaciones(dieta.getObservaciones());
		dto.setFecha(dieta.getFecha());
		dto.setUsuario(dieta.getUsuario().getId());
		
		return dto;
	}
	
	public Dieta toEntity(DietaDTO dto) {
		Dieta dieta = new Dieta();
		
		dieta.setNombre(dto.getNombre());
		dieta.setCheatDay(dto.getCheatDay());
		dieta.setId(dto.getId());
		dieta.setObservaciones(dto.getObservaciones());
		dieta.setUsuario(getUsuarioDTO(dto));
		dieta.setFecha(dto.getFecha());
		
		return dieta;
	}
	
	 public List<DietaDTO> changeListToDTO(List<Dieta> lista) {
	        List<DietaDTO> newListaDtos = new ArrayList<>();
	        for (int i = 0; i < lista.size(); i++) {
	            Dieta dieta = lista.get(i);
	            newListaDtos.add(toDietaDTO(dieta));
	        }
	        return newListaDtos;
	    }
}
