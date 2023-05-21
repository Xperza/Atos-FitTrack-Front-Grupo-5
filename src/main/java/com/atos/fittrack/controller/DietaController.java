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

import com.atos.fittrack.dto.DietaDTO;
import com.atos.fittrack.entities.Dieta;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.DietaMapper;
import com.atos.fittrack.services.DietaService;
import com.atos.fittrack.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/dietas")
public class DietaController {

	@Autowired
	private DietaMapper dietaMapper;
	
	@Autowired
	private DietaService dietaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
    public List<DietaDTO> getAllDietas() {
        return dietaMapper.changeListToDTO(dietaService.findAll());
    }
	
	@GetMapping("/{id}")
    public DietaDTO getDietaById(@PathVariable Integer id) {
        return dietaMapper.toDietaDTO(dietaService.findById(id).get());
    }
	
	@GetMapping("/user/{id}")
	public List<DietaDTO> getDietasUsuario(@PathVariable Integer id) {
		return dietaMapper.changeListToDTO(usuarioService.findById(id).get().getDietas());
	}
	
	@PostMapping
    public ResponseEntity<?> createDieta(@RequestBody DietaDTO dieta) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Usuario> usuario = usuarioService.findById(dieta.getUsuario());
        if (usuario.isPresent()) {
            try {
            	dietaService.save(dietaMapper.toEntity(dieta));
            } catch (DataAccessException e) {
                responseMap.put("mensaje", "Error al añadir en la base de datos");
                responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMap.put("mensaje", "La dieta se ha creado correctamente");
            responseMap.put("dieta", dieta);
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateCalendario(@PathVariable Integer id, @RequestBody DietaDTO dietaDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Dieta> dietaActual = dietaService.findById(id);
        if (dietaActual.isPresent()) {
            Dieta dieta = dietaMapper.toEntity(dietaDTO);
            dieta.setId(id);
            dietaService.save(dieta);
            responseMap.put("mensaje", "La dieta ha sido actualizada");
            responseMap.put("dieta", dietaMapper.toDietaDTO(dieta));
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
        }
    }
	
	 @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteDieta(@PathVariable Integer id) {
	        Map<String, Object> responseMap = new HashMap<>();
	        try {
	            dietaService.delete(id);
	            responseMap.put("mensaje", "La dieta ha sido eliminada correctamente");
	            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	        } catch (DataAccessException e) {
	            responseMap.put("mensaje", "Error al eliminar en la base de datos");
	            responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
	            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
}
