package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.ObjetivoDTO;
import com.atos.fittrack.entities.Ejercicio;
import com.atos.fittrack.entities.Objetivo;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.services.EjercicioService;
import com.atos.fittrack.services.UsuarioService;

@Component
public class ObjetivoMapper {

    @Autowired
    private DietaMapper dietaMapper;
    
    @Autowired
    private SueñoMapper sueñoMapper;
    
    @Autowired
    private UsuarioService usuario;
    
    @Autowired
    private EjercicioMapper ejercicio;
    
    @Autowired
    private EjercicioService servicioEjercicio;
    
    public Ejercicio getEjercicioService(Objetivo objetivo) {
    	return servicioEjercicio.findById(objetivo.getEjercicio().getId()).get();
    }
    
    public Usuario getIdUsuario(Integer idUsuario) {
    	return usuario.findById(idUsuario).get();
    }
    
    public Ejercicio getEjercicio(ObjetivoDTO dto) {
    	return servicioEjercicio.findById(dto.getEjercicio().getId()).get();
    }


    public ObjetivoDTO toObjetivoDTO(Objetivo objetivo) {
    	ObjetivoDTO dto = new ObjetivoDTO();
    	
    	if(objetivo.getEjercicio() != null) {
    		dto.setEjercicio(ejercicio.toEjercicioDTO(getEjercicioService(objetivo)));
    	}
    	if(objetivo.getDieta() != null) {
    		dto.setDieta(dietaMapper.toDietaDTO(objetivo.getDieta()));
    	}
    	if(objetivo.getSueño() != null) {
    		dto.setSueño(sueñoMapper.toSueñoDTO(objetivo.getSueño()));
    	}
    	dto.setDescripcion(objetivo.getDescripcion());
    	dto.setFechaFinal(objetivo.getFechaFinal());
    	dto.setFechaInicio(objetivo.getFechaInicio());
    	dto.setId(objetivo.getId());
    	dto.setNombre(objetivo.getNombre());
    	dto.setUsuario(objetivo.getUsuario().getId());
    	
    	return dto;
    }

    public Objetivo toEntity(ObjetivoDTO dto) {
    	Objetivo objetivo = new Objetivo();
    	
    	objetivo.setDescripcion(dto.getDescripcion());
    	objetivo.setFechaFinal(dto.getFechaFinal());
    	objetivo.setFechaInicio(dto.getFechaInicio());
    	objetivo.setId(dto.getId());
    	if(dto.getEjercicio() != null) {
        	objetivo.setEjercicio(getEjercicio(dto));
    	}
    	if(dto.getDieta() != null) {
           	objetivo.setDieta(dietaMapper.toEntity(dto.getDieta()));
    	}
    	if(dto.getSueño() != null) {
           	objetivo.setSueño(sueñoMapper.toEntity(dto.getSueño()));
    	}
    	objetivo.setNombre(dto.getNombre());
    	objetivo.setUsuario(getIdUsuario(dto.getUsuario()));
    	
    	return objetivo;
    }

    public List<ObjetivoDTO> changeListToDTO(List<Objetivo> lista) {
        List<ObjetivoDTO> newListaDtos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Objetivo objetivo = lista.get(i);
            newListaDtos.add(toObjetivoDTO(objetivo));
        }
        return newListaDtos;
    }
}
