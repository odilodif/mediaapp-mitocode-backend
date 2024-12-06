package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "medico") // Como se va a ver en la base de datos
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedico;

	@Column(name = "nombres", nullable = false, length = 150)
	private String nombres;

	@Column(name = "apellidos", nullable = false, length = 150)
	private String apellidos;

	@Column(name = "CMP", nullable = false, length = 150)
	private String CMP;

	@Column(name = "foto_url", nullable = false)
	private String fotoUrl;

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCMP() {
		return CMP;
	}

	public void setCMP(String cMP) {
		CMP = cMP;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	
	
}
