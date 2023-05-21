package com.atos.fittrack.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CalendarioDTO {

	private int id;

	private String datos;

	private Date fechaFinal;

	private Date fechaInicio;

	private int idUsuario;

	private EjercicioDTO ejercicio;

	public CalendarioDTO() {
	}

	public CalendarioDTO(int id, String datos, Date fechaFinal, Date fechaInicio, int idUsuario,
			EjercicioDTO ejercicio) {
		this.id = id;
		this.datos = datos;
		this.fechaFinal = fechaFinal;
		this.fechaInicio = fechaInicio;
		this.idUsuario = idUsuario;
		this.ejercicio = ejercicio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public EjercicioDTO getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(EjercicioDTO ejercicio) {
		this.ejercicio = ejercicio;
	}
	
}	