package com.atos.fittrack.dto;

public class UsuarioDTO {

	private int id;

	private String apellidos;

	private String email;

	private String nombre;

	private String password;

	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(int id, String apellidos, String email, String nombre, String password) {
		super();
		this.id = id;
		this.apellidos = apellidos;
		this.email = email;
		this.nombre = nombre;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
