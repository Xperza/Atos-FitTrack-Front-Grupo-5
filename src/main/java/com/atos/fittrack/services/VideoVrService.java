package com.atos.fittrack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atos.fittrack.entities.VideoVr;
import com.atos.fittrack.repository.VideoVrRepository;

@Service
public class VideoVrService implements ICRUD<VideoVr, Integer> {

	@Autowired
	private VideoVrRepository videoVrRepository;
	
	@Override
	public List<VideoVr> findAll() {
		return videoVrRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		videoVrRepository.deleteById(id);
	}

	@Override
	public Optional<VideoVr> findById(Integer id) {
		return videoVrRepository.findById(id);
	}

	@Override
	public VideoVr save(VideoVr entity) {
		return videoVrRepository.save(entity);
	}

}
