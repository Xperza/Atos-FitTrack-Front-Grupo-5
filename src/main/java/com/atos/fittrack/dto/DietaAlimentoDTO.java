package com.atos.fittrack.dto;

public class DietaAlimentoDTO {
	
	private Integer id;
	
	private String tipo;
	
	private AlimentoDTO alimento;
	
	private DietaDTO dieta;

	public DietaAlimentoDTO() {
	}

	public DietaAlimentoDTO(Integer id,String tipo, AlimentoDTO alimento, DietaDTO dieta) {
		this.id = id;
		this.tipo = tipo;
		this.alimento = alimento;
		this.dieta = dieta;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public AlimentoDTO getAlimento() {
		return alimento;
	}

	public void setAlimento(AlimentoDTO alimento) {
		this.alimento = alimento;
	}

	public DietaDTO getDieta() {
		return dieta;
	}

	public void setDieta(DietaDTO dieta) {
		this.dieta = dieta;
	}
	
	
}
