package com.mitocode.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity 										// Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "consulta_examen") 				// Como se va a ver en la base de datos
@IdClass(ConsultaExamenPK.class)				//La definicion de estas llaves esta en la Clase ConsultaExamenPK
public class ConsultaExamen {
	@Id
	private Examen examen;
	
	@Id
	private Consulta consulta;

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	
	
	
}
