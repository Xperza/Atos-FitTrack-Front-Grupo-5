package com.atos.fittrack.entities;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
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
 * The persistent class for the sueño database table.
 * 
 */
@Entity
@NamedQuery(name="Sueño.findAll", query="SELECT s FROM Sueño s")
public class Sueño implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="calidad_sueño")
	private String calidadSueño;

	private String comentario;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="hora_final",columnDefinition = "TIME")
	private LocalTime horaFinal;

	@Column(name="hora_inicial",columnDefinition = "TIME")
	private LocalTime horaInicial;

	//bi-directional many-to-one association to Objetivo
	@OneToMany(mappedBy="sueño", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Objetivo> objetivos;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Sueño() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCalidadSueño() {
		return this.calidadSueño;
	}

	public void setCalidadSueño(String calidadSueño) {
		this.calidadSueño = calidadSueño;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public LocalTime getHoraInicial() {
		return this.horaInicial;
	}

	public void setHoraInicial(LocalTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	public List<Objetivo> getObjetivos() {
		return this.objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	public Objetivo addObjetivo(Objetivo objetivo) {
		getObjetivos().add(objetivo);
		objetivo.setSueño(this);

		return objetivo;
	}

	public Objetivo removeObjetivo(Objetivo objetivo) {
		getObjetivos().remove(objetivo);
		objetivo.setSueño(null);

		return objetivo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}