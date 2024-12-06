package com.mitocode.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id	
	private Integer idUsuario;
	
	@Column(name = "username", nullable = false, length = 100)
	private String username;

	@Column(name = "password", nullable = false, length = 150)
	private String password;
	
	@Column(name = "state_enabled", nullable = false, length = 150)
	private String state_enabled;

	@ManyToMany(fetch= FetchType.EAGER) //SELECT * FROM Usuario u where u.nombre = 'mitocode' para el listado de los roles etchType.EAGER para cargar todo y etchType.LAZY para no cargar
	//La tabla que se va a crear es usuario_rol
	@JoinTable(name="usuario_rol", joinColumns = @JoinColumn(name="id_usuario", referencedColumnName = "idUsuario"),inverseJoinColumns = @JoinColumn(name="id_rol", referencedColumnName = "idRol") )
	private List<Rol> roles;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState_enabled() {
		return state_enabled;
	}

	public void setState_enabled(String state_enabled) {
		this.state_enabled = state_enabled;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	
	
}
