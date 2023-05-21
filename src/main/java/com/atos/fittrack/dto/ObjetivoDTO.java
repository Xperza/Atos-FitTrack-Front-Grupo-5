package com.atos.fittrack.dto;

import java.util.Date;

public class ObjetivoDTO {

	private int id;

	private String descripcion;

	private Date fechaFinal;

	private Date fechaInicio;

	private String nombre;
	
	private EjercicioDTO ejercicio;
	
	private int usuario;
	
	private DietaDTO dieta;

	private SueñoDTO sueno;
	
	public ObjetivoDTO() {
		
	}
	
	public ObjetivoDTO(int id, String descripcion, Date fechaFinal, Date fechaInicio, String nombre,
			int usuario, DietaDTO dieta, EjercicioDTO ejercicio, SueñoDTO sueno) {
		this.id = id;
		this.descripcion = descripcion;
		this.fechaFinal = fechaFinal;
		this.fechaInicio = fechaInicio;
		this.nombre = nombre;
		this.usuario = usuario;
		this.dieta = dieta;
		this.ejercicio = ejercicio;
		this.sueno=sueno;
	}


	public SueñoDTO getSueño() {
		return sueno;
	}

	public void setSueño(SueñoDTO sueño) {
		this.sueno = sueño;
	}

	public DietaDTO getDieta() {
		return dieta;
	}

	public void setDieta(DietaDTO dieta) {
		this.dieta = dieta;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EjercicioDTO getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(EjercicioDTO ejercicio) {
		this.ejercicio = ejercicio;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public SueñoDTO getSueno() {
		return sueno;
	}

	public void setSueno(SueñoDTO sueno) {
		this.sueno = sueno;
	}




	
	
}
