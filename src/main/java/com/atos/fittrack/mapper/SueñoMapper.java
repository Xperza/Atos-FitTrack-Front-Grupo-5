package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.SueñoDTO;
import com.atos.fittrack.entities.Sueño;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.services.UsuarioService;

@Component
public class SueñoMapper {

    @Autowired
    private UsuarioService usuario;

    public int getIdUsuario(Sueño sueño) {
    	return usuario.findById(sueño.getUsuario().getId()).get().getId();
    }
    public Usuario getUsuario(SueñoDTO dto) {
    	return usuario.findById(dto.getId_usuario()).get();
    }
    public SueñoDTO toSueñoDTO(Sueño sueño) {
        SueñoDTO dto = new SueñoDTO();

        dto.setId(sueño.getId());
        dto.setCalidadSueño(sueño.getCalidadSueño());
        dto.setComentario(sueño.getComentario());
        dto.setHoraFinal(sueño.getHoraFinal());
        dto.setHoraInicial(sueño.getHoraInicial());
        dto.setId_usuario(getIdUsuario(sueño));
        dto.setFecha(sueño.getFecha());
        
        return dto;
    }

    public Sueño toEntity(SueñoDTO dto) {
        Sueño sueño = new Sueño();
        
        sueño.setId(dto.getId());
        sueño.setCalidadSueño(dto.getCalidadSueño());
        sueño.setComentario(dto.getComentario());
        sueño.setHoraFinal(dto.getHoraFinal());
        sueño.setHoraInicial(dto.getHoraInicial());
        sueño.setUsuario(getUsuario(dto));
        sueño.setFecha(dto.getFecha());
        
        return sueño;
    }

    public List<SueñoDTO> changeListToDTO(List<Sueño> lista) {
        List<SueñoDTO> newListaDtos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Sueño sueño = lista.get(i);
            newListaDtos.add(toSueñoDTO(sueño));
        }
        return newListaDtos;
    }
}