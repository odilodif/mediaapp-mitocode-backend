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

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "menu") // Como se va a ver en la base de datos
public class Menu {
	
	@Id	
	private Integer idMenu;
	
	@Column(name = "icono", nullable = false, length = 100)
	private String icono;

	@Column(name = "nombre", nullable = false, length = 150)
	private String nombre;
	
	@Column(name = "url", nullable = false, length = 150)
	private String url;

	
	@ManyToMany(fetch= FetchType.EAGER) 
	@JoinTable(name="tbl_menu_rol", joinColumns = @JoinColumn(name="id_menu", referencedColumnName = "idMenu"),inverseJoinColumns = @JoinColumn(name="id_rol", referencedColumnName = "idRol") )
	private List<Rol> roles;


	public Integer getIdMenu() {
		return idMenu;
	}


	public void setIdMenu(Integer idMenu) {
		this.idMenu = idMenu;
	}


	public String getIcono() {
		return icono;
	}


	public void setIcono(String icono) {
		this.icono = icono;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public List<Rol> getRoles() {
		return roles;
	}


	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
	
	
	
}
