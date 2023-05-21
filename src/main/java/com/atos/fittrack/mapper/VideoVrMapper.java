package com.atos.fittrack.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atos.fittrack.dto.VideoVrDTO;
import com.atos.fittrack.entities.VideoVr;

@Component
public class VideoVrMapper {

	public VideoVrDTO toVideoVrDTO(VideoVr video) {
		VideoVrDTO dto = new VideoVrDTO();
		
		dto.setDescripcion(video.getDescripcion());
		dto.setId(video.getId());
		dto.setNombre(video.getNombre());
		dto.setVideo(video.getVideo());
		dto.setCalorias(video.getCalorias());
		dto.setDuracion(video.getDuracion());
		
		return dto;
	}
	
	public VideoVr toEntity(VideoVrDTO dto) {
		VideoVr video = new VideoVr();
		
		video.setDescripcion(dto.getDescripcion());
		video.setId(dto.getId());
		video.setNombre(dto.getNombre());
		video.setVideo(dto.getVideo());
		video.setCalorias(dto.getCalorias());
		video.setDuracion(dto.getDuracion());
		
		return video;
	}
	
	public List<VideoVrDTO> changeListToDTO(List<VideoVr> lista) {
        List<VideoVrDTO> newListaDtos = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
        	VideoVr video = lista.get(i);
            newListaDtos.add(toVideoVrDTO(video));
        }
        return newListaDtos;
    }
	
}
