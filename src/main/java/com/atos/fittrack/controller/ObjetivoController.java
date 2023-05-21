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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.fittrack.dto.ObjetivoDTO;
import com.atos.fittrack.entities.Objetivo;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.ObjetivoMapper;
import com.atos.fittrack.mapper.UsuarioMapper;
import com.atos.fittrack.services.ObjetivoService;
import com.atos.fittrack.services.UsuarioService;


@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
public class ObjetivoController {
	
	@Autowired
	private ObjetivoService objetivoService;

	@Autowired
	private ObjetivoMapper objetivoMapper;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	@GetMapping("/objetivo")
	public List<ObjetivoDTO> getObjetivoList(){
		return objetivoMapper.changeListToDTO(objetivoService.findAll());
	}
	
	@GetMapping("/objetivo/{id}")
	public ObjetivoDTO getObjetivoById(@PathVariable Integer id) {
		return objetivoMapper.toObjetivoDTO(objetivoService.findById(id).get());
	}
	
	@GetMapping("/objetivo/user/{id}")
	public List<ObjetivoDTO> getObjetivoByUsuario(@PathVariable Integer id) {
		return objetivoMapper.changeListToDTO(usuarioService.findById(id).get().getObjetivos());
	}
	
	
	@GetMapping("/objetivo/mes")
	public List<ObjetivoDTO> getObjetivoByMes(@RequestParam Integer mes,@RequestParam Integer year, @RequestParam Integer id) {
		return objetivoMapper.changeListToDTO(objetivoService.getIntervaloMes(mes, year,id));
	}
	
	@GetMapping("/objetivo/year")
	public List<ObjetivoDTO> getObjetivoByYear(@RequestParam Integer year, @RequestParam Integer id) {
		return objetivoMapper.changeListToDTO(objetivoService.getIntervaloYear(year,id));
	}
	
	@PostMapping("/objetivo")
	public ResponseEntity<?> addObjetivo(@RequestBody ObjetivoDTO objetivo) {
		Map<String, Object> responseMap = new HashMap<>();
		Optional <Usuario> usuario = usuarioService.findById(objetivo.getUsuario());
		if (usuario.isPresent()){
			try {
				objetivoService.save(objetivoMapper.toEntity(objetivo));
			} catch (DataAccessException e) {
				responseMap.put("mensaje", "Error al añadir en la base de datos");
				responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			responseMap.put("mensaje", "El objetivo ha sido añadido correctamente");
			responseMap.put("objetivo", objetivo);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		}
		responseMap.put("mensaje", "Error al añadir en la base de datos: "+objetivo.getUsuario());
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping("/objetivo")
	public ResponseEntity<?> editObjetivo(@RequestBody ObjetivoDTO objetivo) {
		Map<String, Object> responseMap = new HashMap<>();
		Optional<Objetivo> objetivodto = objetivoService.findById(objetivo.getId());
		if (objetivodto.isPresent()){
			try {
				objetivoService.save(objetivoMapper.toEntity(objetivo));
			} catch (DataAccessException e) {
				responseMap.put("mensaje", "Error al editar en la base de datos");
				responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			responseMap.put("mensaje", "El objetivo ha sido editado correctamente");
			responseMap.put("objetivo", objetivo);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		}
		else {
        return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
		}
		}
	
	@DeleteMapping("/objetivo/{id}")
	public ResponseEntity<?> deleteObjetivo(@PathVariable Integer id) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			objetivoService.delete(id);
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al eliminar en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El objetivo ha sido eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}

}
