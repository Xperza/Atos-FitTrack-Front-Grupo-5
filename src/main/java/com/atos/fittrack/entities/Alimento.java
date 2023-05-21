package com.atos.fittrack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the alimento database table.
 * 
 */
@Entity
@NamedQuery(name="Alimento.findAll", query="SELECT a FROM Alimento a")
public class Alimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String alergenos;

	private double calorias;

	private String ingredientes;

	private String nombre;

	@Column(name="valor_nutricional")
	private String valorNutricional;

	//bi-directional many-to-one association to Dietaalimento
	@OneToMany(mappedBy="alimento", cascade = CascadeType.ALL)
	@JsonManagedReference
	
	
	private List<Dietaalimento> dietaalimentos;

	public Alimento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlergenos() {
		return this.alergenos;
	}

	public void setAlergenos(String alergenos) {
		this.alergenos = alergenos;
	}

	public double getCalorias() {
		return this.calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public String getIngredientes() {
		return this.ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getValorNutricional() {
		return this.valorNutricional;
	}

	public void setValorNutricional(String valorNutricional) {
		this.valorNutricional = valorNutricional;
	}

	public List<Dietaalimento> getDietaalimentos() {
		return this.dietaalimentos;
	}

	public void setDietaalimentos(List<Dietaalimento> dietaalimentos) {
		this.dietaalimentos = dietaalimentos;
	}

	public Dietaalimento addDietaalimento(Dietaalimento dietaalimento) {
		getDietaalimentos().add(dietaalimento);
		dietaalimento.setAlimento(this);

		return dietaalimento;
	}

	public Dietaalimento removeDietaalimento(Dietaalimento dietaalimento) {
		getDietaalimentos().remove(dietaalimento);
		dietaalimento.setAlimento(null);

		return dietaalimento;
	}

}