package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "especialidad") // Como se va a ver en la base de datos
public class Especialidad {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEspecialidad;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
}
