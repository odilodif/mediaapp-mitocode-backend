package com.mitocode.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime fechahora;
	private String mensaje;
	private String detalles;	
	
	public LocalDateTime getFechahora() {
		return fechahora;
	}
	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	public ExceptionResponse(LocalDateTime fechahora, String mensaje, String detalles) {
		super();
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}
	
	

}
