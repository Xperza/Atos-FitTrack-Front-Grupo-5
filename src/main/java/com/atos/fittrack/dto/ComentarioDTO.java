package com.atos.fittrack.dto;

import java.util.Date;

public class ComentarioDTO {
	
	private int id;
	
	private String contenido;
	
	private Date fecha;
	
	private String usuario;
	
	private PostDTO post;
	
	public ComentarioDTO() {
	}

	public ComentarioDTO(int id, String contenido, Date fecha, String usuario, PostDTO post) {
		this.id = id;
		this.contenido = contenido;
		this.post = post;
		this.fecha = fecha;
		this.usuario = usuario;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNomUsuario() {
		return usuario;
	}

	public void setNomUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public PostDTO getPost() {
		return post;
	}

	public void setPost(PostDTO post) {
		this.post = post;
	}
}
