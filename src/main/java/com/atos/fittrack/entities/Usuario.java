package com.atos.fittrack.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message = "No puede estar vacio")
	private String apellidos;
	@NotEmpty(message = "No puede estar vacio")
	@Email
	private String email;

	@NotEmpty(message = "No puede estar vacio")
	private String nombre;

	@NotEmpty(message = "No puede estar vacio")
	@Size(min = 8, message = "El tamaño tiene que tener minimo 8")
	@Pattern(regexp = "^(?=.*[A-Z]).+$", message = "La contraseña debe contener al menos una letra mayúscula")
	@Pattern(regexp = "^(?=.*[a-z]).+$", message = "La contraseña debe contener al menos una letra minúscula")
	@Pattern(regexp = "^(?=.*\\d).+$", message = "La contraseña debe contener al menos una número")
	@Pattern(regexp = "^(?=.*[!@#$%^&*()\\-+_=\\[\\]{}\\\\|;:'\",.<>/?]).+$", message = "La contraseña debe contener al menos un caracter especial")
	private String password;

	// bi-directional many-to-one association to Calendario
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Calendario> calendarios;

	// bi-directional many-to-one association to Dieta
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Dieta> dietas;

	// bi-directional many-to-one association to Ejercicio
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Ejercicio> ejercicios;

	// bi-directional many-to-one association to EjercicioVr
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<EjercicioVr> ejercicioVrs;

	// bi-directional many-to-one association to Objetivo
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Objetivo> objetivos;

	// bi-directional many-to-one association to Post
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Post> posts;

	// bi-directional many-to-one association to Sueño
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Sueño> sueños;

	// bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Comentario> comentario;

	public Usuario() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Calendario> getCalendarios() {
		return this.calendarios;
	}

	public void setCalendarios(List<Calendario> calendarios) {
		this.calendarios = calendarios;
	}

	public Calendario addCalendario(Calendario calendario) {
		getCalendarios().add(calendario);
		calendario.setUsuario(this);

		return calendario;
	}

	public Calendario removeCalendario(Calendario calendario) {
		getCalendarios().remove(calendario);
		calendario.setUsuario(null);

		return calendario;
	}

	public List<Dieta> getDietas() {
		return this.dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	public Dieta addDieta(Dieta dieta) {
		getDietas().add(dieta);
		dieta.setUsuario(this);

		return dieta;
	}

	public Dieta removeDieta(Dieta dieta) {
		getDietas().remove(dieta);
		dieta.setUsuario(null);

		return dieta;
	}

	public List<Ejercicio> getEjercicios() {
		return this.ejercicios;
	}

	public void setEjercicios(List<Ejercicio> ejercicios) {
		this.ejercicios = ejercicios;
	}

	public Ejercicio addEjercicio(Ejercicio ejercicio) {
		getEjercicios().add(ejercicio);
		ejercicio.setUsuario(this);

		return ejercicio;
	}

	public Ejercicio removeEjercicio(Ejercicio ejercicio) {
		getEjercicios().remove(ejercicio);
		ejercicio.setUsuario(null);

		return ejercicio;
	}

	public List<EjercicioVr> getEjercicioVrs() {
		return this.ejercicioVrs;
	}

	public void setEjercicioVrs(List<EjercicioVr> ejercicioVrs) {
		this.ejercicioVrs = ejercicioVrs;
	}

	public EjercicioVr addEjercicioVr(EjercicioVr ejercicioVr) {
		getEjercicioVrs().add(ejercicioVr);
		ejercicioVr.setUsuario(this);

		return ejercicioVr;
	}

	public EjercicioVr removeEjercicioVr(EjercicioVr ejercicioVr) {
		getEjercicioVrs().remove(ejercicioVr);
		ejercicioVr.setUsuario(null);

		return ejercicioVr;
	}

	public List<Objetivo> getObjetivos() {
		return this.objetivos;
	}

	public void setObjetivos(List<Objetivo> objetivos) {
		this.objetivos = objetivos;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post addPost(Post post) {
		getPosts().add(post);
		post.setUsuario(this);

		return post;
	}

	public Post removePost(Post post) {
		getPosts().remove(post);
		post.setUsuario(null);

		return post;
	}

	public List<Sueño> getSueños() {
		return this.sueños;
	}

	public void setSueños(List<Sueño> sueños) {
		this.sueños = sueños;
	}

	public Sueño addSueño(Sueño sueño) {
		getSueños().add(sueño);
		sueño.setUsuario(this);

		return sueño;
	}

	public Sueño removeSueño(Sueño sueño) {
		getSueños().remove(sueño);
		sueño.setUsuario(null);

		return sueño;
	}

}