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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the ejercicio_vr database table.
 * 
 */
@Entity
@Table(name="ejercicio_vr")
@NamedQuery(name="EjercicioVr.findAll", query="SELECT e FROM EjercicioVr e")
public class EjercicioVr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="calorias_quemadas")
	private Double caloriasQuemadas;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to VideoVr
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_video")
	private VideoVr videoVr;

	public EjercicioVr() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getCaloriasQuemadas() {
		return this.caloriasQuemadas;
	}

	public void setCaloriasQuemadas(Double caloriasQuemadas) {
		this.caloriasQuemadas = caloriasQuemadas;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public VideoVr getVideoVr() {
		return this.videoVr;
	}

	public void setVideoVr(VideoVr videoVr) {
		this.videoVr = videoVr;
	}

}