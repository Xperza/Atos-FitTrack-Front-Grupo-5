package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.EjercicioDTO;
import com.atos.fittrack.entities.Ejercicio;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.services.UsuarioService;

@Component
public class EjercicioMapper {
	
    @Autowired
    private UsuarioService usuario;
    
    public int getIdUsuario(Ejercicio ejercicio) {
    	return usuario.findById(ejercicio.getUsuario().getId()).get().getId();
    }
	public Usuario getUsuarioDTO(EjercicioDTO dto) {
		return usuario.findById(dto.getId_usuario()).get();
	}
    public EjercicioDTO toEjercicioDTO(Ejercicio ejercicio) {
        EjercicioDTO dto = new EjercicioDTO();
        
        dto.setId(ejercicio.getId());
        dto.setDescripcion(ejercicio.getDescripcion());
        dto.setMusculos(ejercicio.getMusculos());
        dto.setNombre(ejercicio.getNombre());
        dto.setRepeticiones(ejercicio.getRepeticiones());
        dto.setSeries(ejercicio.getSeries());
        dto.setId_usuario(getIdUsuario(ejercicio));

        return dto;
    }

    public Ejercicio toEntity(EjercicioDTO dto) {
        Ejercicio ejercicio = new Ejercicio();

        ejercicio.setId(dto.getId());
        ejercicio.setDescripcion(dto.getDescripcion());
        ejercicio.setMusculos(dto.getMusculos());
        ejercicio.setNombre(dto.getNombre());
        ejercicio.setRepeticiones(dto.getRepeticiones());
        ejercicio.setSeries(dto.getSeries());
        ejercicio.setUsuario(getUsuarioDTO(dto));

        return ejercicio;
    }

    public List<EjercicioDTO> changeListToDTO(List<Ejercicio> lista) {
        List<EjercicioDTO> newListaDtos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Ejercicio ejercicio = lista.get(i);
            newListaDtos.add(toEjercicioDTO(ejercicio));
        }
        return newListaDtos;
    }
}