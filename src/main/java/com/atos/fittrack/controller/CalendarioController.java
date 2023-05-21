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

import com.atos.fittrack.dto.CalendarioDTO;
import com.atos.fittrack.entities.Calendario;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.CalendarioMapper;
import com.atos.fittrack.services.CalendarioService;
import com.atos.fittrack.services.UsuarioService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/calendario")
public class CalendarioController {

    @Autowired
    private CalendarioService calendarioService;

    @Autowired
    private CalendarioMapper calendarioMapper;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<CalendarioDTO> getAllCalendarios() {
        return calendarioMapper.changeListToDTO(calendarioService.findAll());
    }

    @GetMapping("/{id}")
    public CalendarioDTO getCalendarioById(@PathVariable Integer id) {
        return calendarioMapper.toCalendarioDTO(calendarioService.findById(id).get());
    }
    
    @GetMapping("/user/{id}")
    public List<CalendarioDTO> getCalendarioUsuario(@PathVariable Integer id) {
		return calendarioMapper.changeListToDTO(usuarioService.findById(id).get().getCalendarios());
	}

    @PostMapping
    public ResponseEntity<?> createCalendario(@RequestBody CalendarioDTO calendario) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Usuario> usuario = usuarioService.findById(calendario.getIdUsuario());
        if (usuario.isPresent()) {
            try {
                calendarioService.save(calendarioMapper.toEntity(calendario));
            } catch (DataAccessException e) {
                responseMap.put("mensaje", "Error al añadir en la base de datos");
                responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMap.put("mensaje", "El calendario se ha creado correctamente");
            responseMap.put("calendario", calendario);
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCalendario(@PathVariable Integer id, @RequestBody CalendarioDTO calendarioDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Calendario> calendarioActual = calendarioService.findById(id);
        if (calendarioActual.isPresent()) {
            Calendario calendario = calendarioMapper.toEntity(calendarioDTO);
            calendario.setId(id);
            calendarioService.save(calendario);
            responseMap.put("mensaje", "El calendario ha sido actualizado");
            responseMap.put("calendario", calendarioMapper.toCalendarioDTO(calendario));
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComentario(@PathVariable Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            calendarioService.delete(id);
            responseMap.put("mensaje", "El calendario ha sido eliminado correctamente");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
        } catch (DataAccessException e) {
            responseMap.put("mensaje", "Error al eliminar en la base de datos");
            responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
