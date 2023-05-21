package com.atos.fittrack.dto;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SueñoDTO {
	
	private int id;

	private String calidadSueño;

	private String comentario;

	private Date fecha;
	
	@JsonFormat(pattern= "HH:mm:ss")
	private LocalTime horaFinal;

	@JsonFormat(pattern= "HH:mm:ss")
	private LocalTime horaInicial;
	
	private int id_usuario;

	public SueñoDTO() {
		
	}
	
	public SueñoDTO(int id, String calidadSueño, String comentario, LocalTime horaFinal, LocalTime horaInicial,
		int id_usuario, Date fecha) {
		this.id = id;
		this.calidadSueño = calidadSueño;
		this.comentario = comentario;
		this.horaFinal = horaFinal;
		this.horaInicial = horaInicial;
		this.id_usuario = id_usuario;
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

	public String getCalidadSueño() {
		return calidadSueño;
	}

	public void setCalidadSueño(String calidadSueño) {
		this.calidadSueño = calidadSueño;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public LocalTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalTime horaInicial) {
		this.horaInicial = horaInicial;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	
	
	
}
