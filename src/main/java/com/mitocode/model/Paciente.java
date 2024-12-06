package com.mitocode.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 													// Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "paciente") 									// La Tabla Como se va a ver en la base de datos
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;

	
	@Column(name = "nombres", nullable = false, length = 200)
	private String nombres;

	@Column(name = "apellidos", nullable = false, length = 200)
	private String apellidos;

	@Column(name = "dni", nullable = false, length = 200)
	private String dni;

	@Column(name = "direccion", nullable = false, length = 200)
	private String direccion;

	@Column(name = "telefono", nullable = false, length = 200)
	private String telefono;

	@Column(name = "mail", nullable = false, length = 200)
	private String mail;

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

}
