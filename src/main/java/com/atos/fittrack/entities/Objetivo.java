package com.atos.fittrack.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the objetivo database table.
 * 
 */
@Entity
@NamedQuery(name="Objetivo.findAll", query="SELECT o FROM Objetivo o")
public class Objetivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	private String nombre;

	//bi-directional many-to-one association to Dieta
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_dieta")
	private Dieta dieta;

	//bi-directional many-to-one association to Ejercicio
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_ejercicio")
	private Ejercicio ejercicio;

	//bi-directional many-to-one association to Sueño
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_sueño")
	private Sueño sueño;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Objetivo() {
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

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Dieta getDieta() {
		return this.dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}

	public Ejercicio getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(Ejercicio ejercicio) {
		this.ejercicio = ejercicio;
	}

	public Sueño getSueño() {
		return this.sueño;
	}

	public void setSueño(Sueño sueño) {
		this.sueño = sueño;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}