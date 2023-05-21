package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.UsuarioDTO;
import com.atos.fittrack.entities.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setApellidos(usuario.getApellidos());
        dto.setEmail(usuario.getEmail());
        dto.setNombre(usuario.getNombre());
        dto.setPassword(usuario.getPassword());

        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setApellidos(dto.getApellidos());
        usuario.setEmail(dto.getEmail());
        usuario.setNombre(dto.getNombre());
        usuario.setPassword(dto.getPassword());

        return usuario;
    }

    public List<UsuarioDTO> changeListToDTO(List<Usuario> lista) {
        List<UsuarioDTO> newListaDtos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            Usuario usuario = lista.get(i);
            newListaDtos.add(toUsuarioDTO(usuario));
        }
        return newListaDtos;
    }
}