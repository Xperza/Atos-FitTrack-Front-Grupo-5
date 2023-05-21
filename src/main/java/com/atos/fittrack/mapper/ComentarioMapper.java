package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.ComentarioDTO;
import com.atos.fittrack.entities.Comentario;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.services.PostService;
import com.atos.fittrack.services.UsuarioService;

@Component
public class ComentarioMapper {
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PostService postService;
	
	public ComentarioDTO toComentarioDTO(Comentario comentario) {
		ComentarioDTO dto = new ComentarioDTO();

		dto.setContenido(comentario.getContenido());
		dto.setId(comentario.getId());
		dto.setPost(postMapper.toPostDTO(comentario.getPost()));
		dto.setNomUsuario(comentario.getUsuario().getNombre());
		dto.setFecha(comentario.getFecha());
		return dto;
	}

	public Comentario toEntity(ComentarioDTO dto, Integer id_usuario) {
		Comentario comentario = new Comentario();
		Optional<Usuario> usuario = usuarioService.findById(id_usuario);

		if (usuario.isPresent()) {			
			comentario.setContenido(dto.getContenido());
			comentario.setFecha(dto.getFecha());
			comentario.setId(dto.getId());
			comentario.setUsuario(usuario.get());
			comentario.setPost(postService.findByUsuario(usuario.get()).get());
		}
		
		return comentario;
	}

	public List<ComentarioDTO> changeListToDTO(List<Comentario> lista) {
		List<ComentarioDTO> newListaDtos = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Comentario comentario = lista.get(i);
			newListaDtos.add(toComentarioDTO(comentario));
		}
		return newListaDtos;
	}
}
