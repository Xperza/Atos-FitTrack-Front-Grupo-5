package com.atos.fittrack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.atos.fittrack.dto.AlimentoDTO;
import com.atos.fittrack.entities.Alimento;
import com.atos.fittrack.mapper.AlimentoMapper;
import com.atos.fittrack.services.AlimentoService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

	@Autowired
	private AlimentoMapper alimentosMapper;
	
	@Autowired
	private AlimentoService alimentosService;

	@GetMapping
	public List<AlimentoDTO> getAlimentoList() {
		return alimentosMapper.changeListToDTO(alimentosService.findAll());
	}
	
	@GetMapping("/{id}")
	public AlimentoDTO getAlimentoById(@PathVariable Integer id) {
		return alimentosMapper.toAlimentoDTO(alimentosService.findById(id).get());
	}

	@PostMapping
	public ResponseEntity<?> addAlimento(@RequestBody AlimentoDTO alimento) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			alimentosService.save(alimentosMapper.toEntity(alimento));
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al añadir en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El alimento ha sido añadido correctamente");
		responseMap.put("alimento", alimento);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}

	@PostMapping("/{id}")
	public ResponseEntity<?> updateAlimento(@RequestBody AlimentoDTO alimento, @PathVariable Integer id) {
		Map<String, Object> responseMap = new HashMap<>();
		Alimento dbAlimento = alimentosService.findById(id).get();
		try {
			dbAlimento.setNombre(alimento.getNombre());
			dbAlimento.setAlergenos(alimento.getAlergenos());
			dbAlimento.setIngredientes(alimento.getIngredientes());
			dbAlimento.setValorNutricional(alimento.getValorNutricional());
			dbAlimento.setCalorias(alimento.getCalorias());
			alimentosService.save(dbAlimento);
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al actulizar en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El alimento ha sido actulizar correctamente");
		responseMap.put("alimento", dbAlimento);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}

	@DeleteMapping("/{alimento_id}")
	public ResponseEntity<?> deleteAlimeto(@PathVariable Integer alimento_id) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			alimentosService.delete(alimento_id);
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al eliminar en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El alimento ha sido eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}
}
