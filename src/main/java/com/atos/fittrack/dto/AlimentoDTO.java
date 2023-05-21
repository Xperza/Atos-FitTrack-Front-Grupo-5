package com.atos.fittrack.dto;

public class AlimentoDTO {
	private int id;

	private String nombre;
	
	private String alergenos;

	private String ingredientes;
	
	private double calorias;

	private String valorNutricional;
	
	public AlimentoDTO() {
		
	}

	public AlimentoDTO(int id,String nombre, String alergenos, String ingredientes, double calorias ,String valorNutricional) {
		this.id = id;
		this.nombre=nombre;
		this.alergenos = alergenos;
		this.ingredientes = ingredientes;
		this.calorias = calorias;
		this.valorNutricional = valorNutricional;
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

	public String getAlergenos() {
		return alergenos;
	}

	public void setAlergenos(String alergenos) {
		this.alergenos = alergenos;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}
	

	public Double getCalorias() {
		return calorias;
	}

	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}

	public String getValorNutricional() {
		return valorNutricional;
	}

	public void setValorNutricional(String valorNutricional) {
		this.valorNutricional = valorNutricional;
	}
}
	
