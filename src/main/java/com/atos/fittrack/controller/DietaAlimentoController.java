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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atos.fittrack.dto.DietaAlimentoDTO;
import com.atos.fittrack.entities.Alimento;
import com.atos.fittrack.entities.Dieta;
import com.atos.fittrack.entities.Dietaalimento;
import com.atos.fittrack.mapper.DietaAlimentoMapper;
import com.atos.fittrack.services.AlimentoService;
import com.atos.fittrack.services.DietaAlimentoService;
import com.atos.fittrack.services.DietaService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/dietaAlimentos")
public class DietaAlimentoController {

	@Autowired
	private DietaAlimentoMapper dietaAlimentoMapper;

	@Autowired
	private DietaAlimentoService dietaAlimentoService;

	@Autowired
	private AlimentoService alimentoService;

	@Autowired
	private DietaService dietaService;

	@GetMapping
	public List<DietaAlimentoDTO> getAllDietaAlimentos() {
		return dietaAlimentoMapper.changeListToDTO(dietaAlimentoService.findAll());
	}

	@GetMapping("/alimento/{id}")
	public List<DietaAlimentoDTO> getAllDietaAlimentoByAlimento(@PathVariable Integer id) {
		return dietaAlimentoMapper
				.changeListToDTO(dietaAlimentoService.findAllByAlimento(alimentoService.findById(id).get()));
	}
	
	@GetMapping("/dieta/{id}")
	public List<DietaAlimentoDTO> getAllDietaAlimentoByDieta(@PathVariable Integer id) {
		return dietaAlimentoMapper
				.changeListToDTO(dietaAlimentoService.findAllByDieta(dietaService.findById(id).get()));
	}
	
	@PostMapping
	public ResponseEntity<?> addDietaAlimento(@RequestBody DietaAlimentoDTO dietaAlimentoDTO) {
		Map<String, Object> responseMap = new HashMap<>();
		Optional<Dieta> dietaOptional = dietaService.findById(dietaAlimentoDTO.getDieta().getId());
		Optional<Alimento> alimentoOptional = alimentoService.findById(dietaAlimentoDTO.getAlimento().getId());
		if (dietaOptional.isPresent() && alimentoOptional.isPresent()) {
			try {
				dietaAlimentoService.save(dietaAlimentoMapper.toEntity(dietaAlimentoDTO));
			} catch (DataAccessException e) {
				responseMap.put("mensaje", "Error al añadir en la base de datos");
				responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			responseMap.put("mensaje", "La dietaAlimento ha sido añadida correctamente");
			responseMap.put("dietaAlimento", dietaAlimentoDTO);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/list")
	public ResponseEntity<?> addDietaAlimentos(@RequestBody List<DietaAlimentoDTO> dietaAlimentosDTO) {
		Map<String, Object> responseMap = new HashMap<>();
		if (!dietaAlimentosDTO.isEmpty()) {
			try {
				for(DietaAlimentoDTO dietaAlimentoDTO:dietaAlimentosDTO) {
					dietaAlimentoService.save(dietaAlimentoMapper.toEntity(dietaAlimentoDTO));
				}
			} catch (DataAccessException e) {
				responseMap.put("mensaje", "Error al añadir en la base de datos");
				responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			responseMap.put("mensaje", "Las dietaAlimentos han sido añadidas correctamente");
			responseMap.put("dietaAlimento", dietaAlimentosDTO);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
		}
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping
	public ResponseEntity<?> updateDietaAlimento(@RequestBody DietaAlimentoDTO dietaAlimentoDTO) {
		Map<String, Object> responseMap = new HashMap<>();
		Optional<Dietaalimento> dietaAlimentoActual = dietaAlimentoService.findById(dietaAlimentoDTO.getId());
		if (dietaAlimentoActual.isPresent()) {
			Dietaalimento dietaAlimento = dietaAlimentoMapper.toEntity(dietaAlimentoDTO);
			dietaAlimentoService.save(dietaAlimento);
			responseMap.put("mensaje", "La DietaAlimento ha sido actualizada correctamente");
			responseMap.put("dietaAlimento", dietaAlimentoMapper.toDietaAlimentoDTO(dietaAlimento));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteDietaAlimento(@PathVariable Integer id) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			dietaAlimentoService.delete(id);
			responseMap.put("mensaje", "La DietaAlimento ha sido eliminado correctamente");
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al eliminar en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
