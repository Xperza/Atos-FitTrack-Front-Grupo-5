package com.atos.fittrack.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the dieta database table.
 * 
 */
@Entity
@NamedQuery(name="Dieta.findAll", query="SELECT d FROM Dieta d")
public class Dieta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="cheat_day")
	private String cheatDay;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private String nombre;

	private String observaciones;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Dietaalimento
	@OneToMany(mappedBy="dieta", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Dietaalimento> dietaalimentos;

	//bi-directional many-to-one association to Objetivo
	@OneToMany(mappedBy="dieta", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Objetivo> objetivos;

	public Dieta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCheatDay() {
		return this.cheatDay;
	}

	public void setCheatDay(String cheatDay) {
		this.cheatDay = cheatDay;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Dietaalimento> getDietaalimentos() {
		return this.dietaalimentos;
	}

	public void setDietaalimentos(List<Dietaalimento> dietaalimentos) {
		this.dietaalimentos = dietaalimentos;
	}

	public Dietaalimento addDietaalimento(Dietaalimento dietaalimento) {
		getDietaalimentos().add(dietaalimento);
		dietaalimento.setDieta(this);

		return dietaalimento;
	}

	public Dietaalimento removeDietaalimento(Dietaalimento dietaalimento) {
		getDietaalimentos().remove(dietaalimento);
		dietaalimento.setDieta(null);

		return dietaalimento;
	}

	public List<Objetivo> getObjetivos() {
		return this.objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	public Objetivo addObjetivo(Objetivo objetivo) {
		getObjetivos().add(objetivo);
		objetivo.setDieta(this);

		return objetivo;
	}

	public Objetivo removeObjetivo(Objetivo objetivo) {
		getObjetivos().remove(objetivo);
		objetivo.setDieta(null);

		return objetivo;
	}

}