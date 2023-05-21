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

import com.atos.fittrack.dto.VideoVrDTO;
import com.atos.fittrack.entities.VideoVr;
import com.atos.fittrack.mapper.VideoVrMapper;
import com.atos.fittrack.services.VideoVrService;

@CrossOrigin(origins = {"http://localhost:4200" ,"http://192.168.1.113:4200"})
@RestController
@RequestMapping("/videovr")
public class VideoVrController {

	@Autowired
	private VideoVrMapper videoVrMapper;

	@Autowired
	private VideoVrService videoVrService;

	@GetMapping
	public List<VideoVrDTO> getAllVideoVr() {
		return videoVrMapper.changeListToDTO(videoVrService.findAll());
	}

	@GetMapping("{id}")
	public VideoVrDTO getVideoVrById(@PathVariable Integer id) {
		return videoVrMapper.toVideoVrDTO(videoVrService.findById(id).get());
	}

	@PostMapping
	public ResponseEntity<?> createVideoVr(@RequestBody VideoVrDTO videoVr) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			videoVrService.save(videoVrMapper.toEntity(videoVr));
		} catch (DataAccessException e) {
			responseMap.put("mensaje", "Error al añadir en la base de datos");
			responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put("mensaje", "El videoVr se ha creado correctamente");
		responseMap.put("videoVr", videoVr);
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> updateVideoVr(@PathVariable Integer id, @RequestBody VideoVrDTO videoVrDTO) {
        Map<String, Object> responseMap = new HashMap<>();
        Optional<VideoVr> videoVrActual = videoVrService.findById(id);
        if (videoVrActual.isPresent()) {
            VideoVr videoVr = videoVrMapper.toEntity(videoVrDTO);
            videoVr.setId(id);
            videoVrService.save(videoVr);
            responseMap.put("mensaje", "El videoVr ha sido actualizado");
            responseMap.put("videoVr", videoVrMapper.toVideoVrDTO(videoVr));
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } else {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideoVr(@PathVariable Integer id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            videoVrService.delete(id);
            responseMap.put("mensaje", "El videoVr ha sido eliminado correctamente");
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
        } catch (DataAccessException e) {
            responseMap.put("mensaje", "Error al eliminar en la base de datos");
            responseMap.put("error", e.getMessage().concat(": " + e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
