package com.atos.fittrack.dto;

import java.util.Date;

public class PostDTO {

	private int id;

	private String descripcion;

	private String nombre;
	
	private String usuario;
	
	private Date fecha;
	
	public PostDTO() {
		
	}
	
	public PostDTO(int id, String descripcion, String nombre, UsuarioDTO usuario, Date fecha) {
		this.id = id;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.usuario = usuario.getNombre();
		this.fecha = fecha;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
