package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "examen") // Como se va a ver en la base de datos
public class Examen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idExamen;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "descripcion", nullable = false, length = 150)
	private String descripcion;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idExamen == null) ? 0 : idExamen.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Examen other = (Examen) obj;
		if (idExamen == null) {
			if (other.idExamen != null)
				return false;
		} else if (!idExamen.equals(other.idExamen))
			return false;
		return true;
	}

	public Integer getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
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
