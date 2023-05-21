package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.PostDTO;
import com.atos.fittrack.entities.Post;
import com.atos.fittrack.services.UsuarioService;

@Component
public class PostMapper {
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@Autowired
	private UsuarioService usuarioService;

	public PostDTO toPostDTO(Post post) {
		PostDTO dto = new PostDTO();
		
		dto.setDescripcion(post.getDescripcion());
		dto.setId(post.getId());
		dto.setNombre(post.getNombre());
		dto.setUsuario(usuarioMapper.toUsuarioDTO(post.getUsuario()).getNombre());
		dto.setFecha(post.getFecha());
		
		return dto;
	}

	public Post toEntity(PostDTO dto, Integer id_usuario) {
		Post post = new Post();
		
		post.setDescripcion(dto.getDescripcion());
		post.setFecha(dto.getFecha());
		post.setId(dto.getId());
		post.setNombre(dto.getNombre());
		post.setUsuario(usuarioService.findById(id_usuario).get());
		
		return post;
	}

	public List<PostDTO> changeListToDTO(List<Post> lista) {
		List<PostDTO> newListaDtos = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			Post post = lista.get(i);
			newListaDtos.add(toPostDTO(post));
		}
		return newListaDtos;
	}
}
