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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atos.fittrack.dto.EjercicioVrDTO;
import com.atos.fittrack.dto.ObjetivoDTO;
import com.atos.fittrack.entities.EjercicioVr;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.EjercicioVrMapper;
import com.atos.fittrack.services.EjercicioVrService;
import com.atos.fittrack.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/ejerciciovr")
public class EjercicioVrController {

	@Autowired
	private EjercicioVrMapper ejercicioVrMapper;
	
	@Autowired
	private EjercicioVrService ejercicioVrService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
    public List<EjercicioVrDTO> getAllEjercicioVr() {
        return ejercicioVrMapper.changeListToDTO(ejercicioVrService.findAll());
    }
	
	@GetMapping("{id}")
	public EjercicioVrDTO getEjercicioVrById(@PathVariable Integer id) {
		return ejercicioVrMapper.toEjercicioVrDTO(ejercicioVrService.findById(id).get());
	}
	
	@GetMapping("user/{id}")
	public List<EjercicioVrDTO>  getEjercicioVrByUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if(usuario.isPresent()) {
    		return ejercicioVrMapper.changeListToDTO(ejercicioVrService.findByUsuario(usuario.get()));
        }
        return null;
	}
	@GetMapping("user/estadisticas/mes")
	public List<EjercicioVrDTO> getEjercicioVrByMes(@RequestParam Integer mes,@RequestParam Integer year, @RequestParam Integer id) {
		return ejercicioVrMapper.changeListToDTO(ejercicioVrService.findEjercicio_VrMensual(mes,year,id));
	}
	@GetMapping("user/estadisticas/year")
	public List<EjercicioVrDTO> getEjercicioVrByYear(@RequestParam Integer year, @RequestParam Integer id) {
		return ejercicioVrMapper.changeListToDTO(ejercicioVrService.findEjercicio_VrAnual(year, id));
	}
	
	@PostMapping
    public ResponseEntity<?> createEjercicioVr(@RequestBody EjercicioVrDTO ejercicioVr) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Usuario> usuario = usuarioService.findById(ejercicioVr.getId_usuario());
        if (usuario.isPresent()) {
            try {
            	ejercicioVrService.save(ejercicioVrMapper.toEntity(ejercicioVr));
            } catch (DataAccessException e) {
                responseMap.put("mensaje", "Error al añadir en la base de datos");
                responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMap.put("mensaje", "El ejercicioVr se ha creado correctamente");
            responseMap.put("ejercicioVr", ejercicioVr);
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateEjercicioVr(@PathVariable Integer id, @RequestBody EjercicioVrDTO ejercicioVrDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<EjercicioVr> ejercicioVrActual = ejercicioVrService.findById(id);
        if (ejercicioVrActual.isPresent()) {
            EjercicioVr ejercicioVr = ejercicioVrMapper.toEntity(ejercicioVrDTO);
            ejercicioVr.setId(id);
            ejercicioVrService.save(ejercicioVr);
            responseMap.put("mensaje", "El ejercicioVr ha sido actualizado");
            responseMap.put("ejercicioVr", ejercicioVrMapper.toEjercicioVrDTO(ejercicioVr));
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEjercicioVr(@PathVariable Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            ejercicioVrService.delete(id);
            responseMap.put("mensaje", "El ejercicioVr ha sido eliminado correctamente");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
        } catch (DataAccessException e) {
            responseMap.put("mensaje", "Error al eliminar en la base de datos");
            responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
