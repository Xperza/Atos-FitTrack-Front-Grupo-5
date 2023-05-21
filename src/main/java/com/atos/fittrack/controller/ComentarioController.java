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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.fittrack.dto.ComentarioDTO;
import com.atos.fittrack.entities.Post;
import com.atos.fittrack.mapper.ComentarioMapper;
import com.atos.fittrack.services.ComentarioService;
import com.atos.fittrack.services.PostService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

	@Autowired
	private ComentarioMapper comentarioMapper;
	
	@Autowired
	private ComentarioService service;
	
	@Autowired
	private PostService postService;

	@GetMapping
	public List<ComentarioDTO> index(){
		return comentarioMapper.changeListToDTO(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ComentarioDTO getComentarioById(@PathVariable Integer id) {
		return comentarioMapper.toComentarioDTO(service.findById(id).get());
	}
	
	@GetMapping("all/{id}")
	public List<ComentarioDTO> getAllComentarioByPostId(@PathVariable Integer id) {
		return comentarioMapper.changeListToDTO(postService.findById(id).get().getComentarios());
	}
	
	@PostMapping
	public ResponseEntity<?> addComentario(@RequestBody ComentarioDTO comentario, @RequestParam Integer id_post, @RequestParam Integer id_usuario) {
		Map<String, Object> responseMap = new HashMap<>();
		Optional<Post> post = postService.findById(id_post);
		if (post.isPresent()) {
			try {
				service.save(comentarioMapper.toEntity(comentario, id_usuario));
			} catch (DataAccessException e) {
				responseMap.put("mensaje", "Error al añadir en la base de datos");
				responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			responseMap.put("mensaje", "El comentario ha sido añadido correctamente");
			responseMap.put("comentario", comentario);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteComentario(@RequestParam Integer comentario_id) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			service.delete(comentario_id);
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al eliminar en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El comentario ha sido eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}
}
