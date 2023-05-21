package com.atos.fittrack.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the dietaalimento database table.
 * 
 */
@Entity
@NamedQuery(name="Dietaalimento.findAll", query="SELECT d FROM Dietaalimento d")
public class Dietaalimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String tipo;

	//bi-directional many-to-one association to Alimento
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_alimento")
	private Alimento alimento;

	//bi-directional many-to-one association to Dieta
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_dieta")
	private Dieta dieta;

	public Dietaalimento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Alimento getAlimento() {
		return this.alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Dieta getDieta() {
		return this.dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

}