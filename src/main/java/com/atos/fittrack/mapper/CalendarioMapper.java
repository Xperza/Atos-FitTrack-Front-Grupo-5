package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.CalendarioDTO;
import com.atos.fittrack.entities.Calendario;
import com.atos.fittrack.services.UsuarioService;

@Component
public class CalendarioMapper {

    @Autowired
    private EjercicioMapper ejercicioMapper;

    @Autowired
    private UsuarioService usuario;

    public CalendarioDTO toCalendarioDTO(Calendario calendario){

        CalendarioDTO dto = new CalendarioDTO();

        dto.setId(calendario.getId());
        dto.setDatos(calendario.getDatos());
        dto.setFechaInicio(calendario.getFechaInicio());
        dto.setFechaFinal(calendario.getFechaFinal());
        dto.setEjercicio(ejercicioMapper.toEjercicioDTO(calendario.getEjercicio()));
        dto.setIdUsuario(calendario.getUsuario().getId());

        return dto;

    }

    public Calendario toEntity(CalendarioDTO dto){

        Calendario calendario = new Calendario();

        calendario.setId(dto.getId());
        calendario.setDatos(dto.getDatos());
        calendario.setFechaInicio(dto.getFechaInicio());
        calendario.setFechaFinal(dto.getFechaFinal());
        calendario.setUsuario(usuario.findById(dto.getId()).get());
        calendario.setEjercicio(ejercicioMapper.toEntity(dto.getEjercicio()));

        return calendario;
    }

    public List<CalendarioDTO> changeListToDTO(List<Calendario> lista) {

        List<CalendarioDTO> newListaDtos = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Calendario calendario = lista.get(i);
            newListaDtos.add(toCalendarioDTO(calendario));
        }

        return newListaDtos;

    }

}
