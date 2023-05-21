package com.atos.fittrack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.fittrack.dto.ComentarioDTO;
import com.atos.fittrack.dto.PostDTO;
import com.atos.fittrack.entities.Post;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.ComentarioMapper;
import com.atos.fittrack.mapper.PostMapper;
import com.atos.fittrack.services.PostService;
import com.atos.fittrack.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
public class PostController {

	@Autowired
	private PostMapper mapper;
	
	@Autowired
	private PostService service;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ComentarioMapper comentarioMapper;
	
	@GetMapping("/post")
	public List<PostDTO> getPostList() {
		return mapper.changeListToDTO(service.findAll());
	}
	
	@GetMapping("/post/{id}")
	public PostDTO getPostById(@PathVariable Integer id) {
		return mapper.toPostDTO(service.findById(id).get());
	}
	
	@GetMapping("/post/user/{id}")
	public PostDTO getPostByUserId(@PathVariable Integer id) {
		return mapper.toPostDTO(service.findByUsuario(usuarioService.findById(id).get()).get());
	} 
	
	@GetMapping("/post/{id}/comentarios")
	public List<ComentarioDTO> getComentariosByPost(@PathVariable Integer id) {
		Post post = service.findById(id).get();
		return comentarioMapper.changeListToDTO(post.getComentarios());
	}
	
	@PostMapping("/post/{id}")
	public ResponseEntity<?> addPost(@RequestBody PostDTO post, @PathVariable Integer id) {
		Optional<Usuario> usuOptional = usuarioService.findById(id);
		Map<String, Object> responseMap = new HashMap<>();		
		if(usuOptional.isPresent()) {
			try {
				service.save(mapper.toEntity(post, id));
			} catch (DataAccessException e) {
				responseMap.put("mensaje", "Error al añadir en la base de datos");
				responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			responseMap.put("mensaje", "El post ha sido añadido correctamente");
			responseMap.put("post", post);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);	
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/post")
	public ResponseEntity<?> deletePost(@RequestParam Integer post_id) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			service.delete(post_id);
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al eliminar en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El post ha sido eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}
}
