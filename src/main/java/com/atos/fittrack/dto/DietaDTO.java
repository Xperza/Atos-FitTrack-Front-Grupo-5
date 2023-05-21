package com.atos.fittrack.dto;

import java.util.Date;

public class DietaDTO {

	private int id;
	
	private String nombre;
	
	private String cheatDay;
	
	private String observaciones;
	
	private int usuario;

	private Date fecha;
	
	public DietaDTO() {
		
	}
	
	public DietaDTO(int id, String nombre, String cheatDay, String observaciones, int usuario, Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.cheatDay = cheatDay;
		this.observaciones = observaciones;
		this.usuario = usuario;
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCheatDay() {
		return cheatDay;
	}

	public void setCheatDay(String cheatDay) {
		this.cheatDay = cheatDay;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
