package com.atos.fittrack.dto;

import java.util.Date;

public class EjercicioVrDTO {

	private int id;
	
	private Double caloriasQuemadas;
	
	private Date fecha;
	
	private int id_usuario;
	
	private VideoVrDTO videoVr;

	public EjercicioVrDTO() {
		
	}

	public EjercicioVrDTO(int id, Date fecha, int id_usuario, VideoVrDTO videoVr, Double caloriasQuemadas) {

		this.id = id;
		this.fecha = fecha;
		this.id_usuario = id_usuario;
		this.videoVr = videoVr;
		this.caloriasQuemadas = caloriasQuemadas;
	}
	
	public Double getCaloriasQuemadas() {
		return caloriasQuemadas;
	}

	public void setCaloriasQuemadas(Double caloriasQuemadas) {
		this.caloriasQuemadas = caloriasQuemadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public VideoVrDTO getVideoVr() {
		return videoVr;
	}

	public void setVideoVr(VideoVrDTO videoVr) {
		this.videoVr = videoVr;
	}
	
	
}
