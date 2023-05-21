package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.EjercicioVrDTO;
import com.atos.fittrack.entities.EjercicioVr;
import com.atos.fittrack.services.UsuarioService;

@Component
public class EjercicioVrMapper {

	@Autowired
	private VideoVrMapper videoVrMapper;
	
	@Autowired
	private UsuarioService usuario;
	
	public EjercicioVrDTO toEjercicioVrDTO(EjercicioVr ejercicio) {
		EjercicioVrDTO dto = new EjercicioVrDTO();
		
		dto.setFecha(ejercicio.getFecha());
		dto.setId(ejercicio.getId());
		dto.setId_usuario(ejercicio.getUsuario().getId());
		dto.setVideoVr(videoVrMapper.toVideoVrDTO(ejercicio.getVideoVr()));
		dto.setCaloriasQuemadas(ejercicio.getCaloriasQuemadas());
		
		return dto;
	}
	
	public EjercicioVr toEntity(EjercicioVrDTO dto) {
		EjercicioVr ejercicio = new EjercicioVr();
		
		ejercicio.setFecha(dto.getFecha());
		ejercicio.setId(dto.getId());
		ejercicio.setUsuario(usuario.findById(dto.getId_usuario()).get());
		ejercicio.setVideoVr(videoVrMapper.toEntity(dto.getVideoVr()));
		ejercicio.setCaloriasQuemadas(dto.getCaloriasQuemadas());
		
		return ejercicio;
	}

	public List<EjercicioVrDTO> changeListToDTO(List<EjercicioVr> lista) {
        List<EjercicioVrDTO> newListaDtos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            EjercicioVr ejercicio = lista.get(i);
            newListaDtos.add(toEjercicioVrDTO(ejercicio));
        }
        return newListaDtos;
    }
	
}
