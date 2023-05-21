package com.atos.fittrack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the ejercicio database table.
 * 
 */
@Entity
@NamedQuery(name="Ejercicio.findAll", query="SELECT e FROM Ejercicio e")
public class Ejercicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String musculos;

	private String nombre;

	private int repeticiones;

	private int series;

	//bi-directional many-to-one association to Calendario
	@OneToMany(mappedBy="ejercicio", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Calendario> calendarios;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to Objetivo
	@OneToMany(mappedBy="ejercicio", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Objetivo> objetivos;

	public Ejercicio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMusculos() {
		return this.musculos;
	}

	public void setMusculos(String musculos) {
		this.musculos = musculos;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRepeticiones() {
		return this.repeticiones;
	}

	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}

	public int getSeries() {
		return this.series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

	public List<Calendario> getCalendarios() {
		return this.calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

	public Calendario addCalendario(Calendario calendario) {
		getCalendarios().add(calendario);
		calendario.setEjercicio(this);

		return calendario;
	}

	public Calendario removeCalendario(Calendario calendario) {
		getCalendarios().remove(calendario);
		calendario.setEjercicio(null);

		return calendario;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Objetivo> getObjetivos() {
		return this.objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	public Objetivo addObjetivo(Objetivo objetivo) {
		getObjetivos().add(objetivo);
		objetivo.setEjercicio(this);

		return objetivo;
	}

	public Objetivo removeObjetivo(Objetivo objetivo) {
		getObjetivos().remove(objetivo);
		objetivo.setEjercicio(null);

		return objetivo;
	}

}