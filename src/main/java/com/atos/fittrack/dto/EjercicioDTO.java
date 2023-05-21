package com.atos.fittrack.dto;

public class EjercicioDTO {

	private int id;

	private String descripcion;

	private String musculos;

	private String nombre;

	private int repeticiones;

	private int series;
	
	private int id_usuario;

	public EjercicioDTO() {
		
	}
	
	public EjercicioDTO(int id, String descripcion, String musculos, String nombre, int repeticiones, int series,
			int id_usuario) {
		this.id = id;
		this.descripcion = descripcion;
		this.musculos = musculos;
		this.nombre = nombre;
		this.repeticiones = repeticiones;
		this.series = series;
		this.id_usuario = id_usuario;
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

	public String getMusculos() {
		return musculos;
	}

	public void setMusculos(String musculos) {
		this.musculos = musculos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	
	
}
