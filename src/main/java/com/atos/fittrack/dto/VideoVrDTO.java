package com.atos.fittrack.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VideoVrDTO {

	private int id;
	
	private String descripcion;
	
	private String nombre;
	
	private String video;
	
	@JsonFormat(pattern= "HH:mm:ss")
	private LocalTime duracion;

	private Double calorias;
	
	public VideoVrDTO() {
		
	}

	public VideoVrDTO(int id, String descripcion, String nombre, String video, LocalTime duracion, Double calorias) {
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.video = video;
		this.duracion = duracion;
		this.calorias = calorias;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public Double getCalorias() {
		return calorias;
	}

	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	
}
