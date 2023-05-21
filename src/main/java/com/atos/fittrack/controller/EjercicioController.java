package com.atos.fittrack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.web.bind.annotation.*;

import com.atos.fittrack.dto.EjercicioDTO;
import com.atos.fittrack.entities.Ejercicio;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.EjercicioMapper;
import com.atos.fittrack.services.EjercicioService;
import com.atos.fittrack.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
public class EjercicioController {

    @Autowired
    private EjercicioService ejercicioService;

    @Autowired
    private EjercicioMapper ejercicioMapper;
    
    @Autowired
    private UsuarioService usuario;

    @GetMapping("/ejercicios")
    public List<EjercicioDTO> getEjercicios() {
       return ejercicioMapper.changeListToDTO(ejercicioService.findAll());
    }

    @GetMapping("/ejercicios/{id}")
    public EjercicioDTO getEjercicioById(@PathVariable Integer id) {
        return ejercicioMapper.toEjercicioDTO(ejercicioService.findById(id).get());
    }
    
    @GetMapping("/ejercicios/usuario/{id_usuario}")
    public List<EjercicioDTO> getEjercicioByUserId(@PathVariable Integer id_usuario) {
    	Usuario usuarioEjercicio = usuario.findById(id_usuario).get();        
    	return ejercicioMapper.changeListToDTO(ejercicioService.findByUsuario(usuarioEjercicio));
    }

    @PostMapping("/ejercicios")
    public ResponseEntity<?> addEjercicio(@RequestBody EjercicioDTO ejercicioDTO, @RequestParam Integer id_usuario) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Usuario> usuarioOptional = usuario.findById(id_usuario);
        if(usuarioOptional.isPresent()) {
            try {
                ejercicioService.save(ejercicioMapper.toEntity(ejercicioDTO));
            } catch (DataAccessException e) {
                responseMap.put("mensaje", "Error al añadir en la base de datos");
                responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMap.put("mensaje", "El ejercicio ha sido añadido correctamente");
            responseMap.put("ejercicio", ejercicioDTO);
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
        }
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/ejercicios/{id}")
    public ResponseEntity<?> updateEjercicio(@PathVariable Integer id, @RequestBody EjercicioDTO ejercicioDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Ejercicio> ejercicioActual = ejercicioService.findById(id);
        if (ejercicioActual.isPresent()) {
            Ejercicio ejercicio = ejercicioMapper.toEntity(ejercicioDTO);
            ejercicio.setId(id);
            ejercicioService.save(ejercicio);
            responseMap.put("mensaje", "El ejercicio ha sido actualizado correctamente");
            responseMap.put("ejercicio", ejercicioMapper.toEjercicioDTO(ejercicio));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ejercicios/{id}")
    public ResponseEntity<?> deleteEjercicio(@PathVariable Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            ejercicioService.delete(id);
            responseMap.put("mensaje", "El ejercicio ha sido eliminado correctamente");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
        } catch (DataAccessException e) {
            responseMap.put("mensaje", "Error al eliminar en la base de datos");
            responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
