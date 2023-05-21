package com.atos.fittrack.entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the video_vr database table.
 * 
 */
@Entity
@Table(name="video_vr")
@NamedQuery(name="VideoVr.findAll", query="SELECT v FROM VideoVr v")
public class VideoVr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String descripcion;

	private String nombre;

	private String video;
	
	@Column(name="duracion",columnDefinition = "TIME")
	private LocalTime duracion;

	private Double calorias;
	
	//bi-directional many-to-one association to EjercicioVr
	@OneToMany(mappedBy="videoVr", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EjercicioVr> ejercicioVrs;

	public VideoVr() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalTime getDuracion() {
		return duracion;
	}

	public void setDuracion(LocalTime duracion) {
		this.duracion = duracion;
	}

	public Double getCalorias() {
		return calorias;
	}

	public void setCalorias(Double calorias) {
		this.calorias = calorias;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public List<EjercicioVr> getEjercicioVrs() {
		return this.ejercicioVrs;
	}

	public void setEjercicioVrs(List<EjercicioVr> ejercicioVrs) {
		this.ejercicioVrs = ejercicioVrs;
	}

	public EjercicioVr addEjercicioVr(EjercicioVr ejercicioVr) {
		getEjercicioVrs().add(ejercicioVr);
		ejercicioVr.setVideoVr(this);

		return ejercicioVr;
	}

	public EjercicioVr removeEjercicioVr(EjercicioVr ejercicioVr) {
		getEjercicioVrs().remove(ejercicioVr);
		ejercicioVr.setVideoVr(null);

		return ejercicioVr;
	}

}