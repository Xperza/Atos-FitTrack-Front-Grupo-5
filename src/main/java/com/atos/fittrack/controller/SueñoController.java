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

import com.atos.fittrack.dto.SueñoDTO;
import com.atos.fittrack.entities.Sueño;
import com.atos.fittrack.entities.Usuario;
import com.atos.fittrack.mapper.SueñoMapper;
import com.atos.fittrack.services.SueñoService;
import com.atos.fittrack.services.UsuarioService;

@RestController
@RequestMapping("/sueños")
@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
public class SueñoController {

    @Autowired
    private SueñoService sueñoService;

    @Autowired
    private SueñoMapper sueñoMapper;
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<SueñoDTO> obtenerTodos() {
    	return sueñoMapper.changeListToDTO(sueñoService.findAll());
    }

    @GetMapping("/{id}")
    public SueñoDTO obtenerPorId(@PathVariable("id") Integer id) {
    	return sueñoMapper.toSueñoDTO(sueñoService.findById(id).get());
    }
    
    @GetMapping("/user/{id}")
    public SueñoDTO obtenerPorUsuario(@PathVariable("id") Integer id) {
    	Optional<Usuario> suenoUsuario = usuarioService.findById(id);
    	SueñoDTO dto = new SueñoDTO();
    	if(suenoUsuario.isPresent()) {
    		dto= sueñoMapper.toSueñoDTO(sueñoService.findByUsuario(suenoUsuario.get()).get());
    	}
    	return dto;
    	
    }

    @PostMapping
    public ResponseEntity<?> addSueño(@RequestBody SueñoDTO sueñoDTO, @RequestParam Integer id_usuario) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Usuario> usuario = usuarioService.findById(id_usuario);
        if (usuario.isPresent()) {
            try {
                sueñoService.save(sueñoMapper.toEntity(sueñoDTO));
            } catch (DataAccessException e) {
                responseMap.put("mensaje", "Error al añadir en la base de datos");
                responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            responseMap.put("mensaje", "El sueño ha sido añadido correctamente");
            responseMap.put("sueño", sueñoDTO);
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
        }
        return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSueño(@PathVariable Integer id, @RequestBody SueñoDTO sueñoDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<Sueño> optionalSueño = sueñoService.findById(id);
        if (!optionalSueño.isPresent()) {
            responseMap.put("mensaje", "El sueño con ID " + id + " no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.NOT_FOUND);
        }
        Sueño sueño = optionalSueño.get();
        try {
            sueño.setCalidadSueño(sueñoDTO.getCalidadSueño());
            sueño.setComentario(sueñoDTO.getComentario());
            sueño.setHoraInicial(sueñoDTO.getHoraInicial());
            Optional<Usuario> optionalUsuario = usuarioService.findById(sueñoDTO.getId_usuario());
            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();
                sueño.setUsuario(usuario);
            } else {
                responseMap.put("mensaje", "El usuario con ID " + sueñoDTO.getId_usuario() + " no existe en la base de datos");
                return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            sueño.setHoraFinal(sueñoDTO.getHoraFinal());
            sueñoService.save(sueño);
            responseMap.put("mensaje", "El sueño ha sido actualizado correctamente");
            responseMap.put("sueño", sueñoMapper.toSueñoDTO(sueño));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
        } catch (DataAccessException e) {
            responseMap.put("mensaje", "Error al actualizar el sueño");
            responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Optional<Sueño> sueño = sueñoService.findById(id);
        if (sueño.isPresent()) {
            sueñoService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
