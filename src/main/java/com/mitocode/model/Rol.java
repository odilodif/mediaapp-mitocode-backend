package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "rol") // Como se va a ver en la base de datos
public class Rol {
	
	@Id	
	private Integer idRol;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", nullable = false, length = 150)
	private String descripcion;

	
	
	
	
	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
