package com.mitocode.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "detalle_consulta") // Como se va a ver en la base de datos
public class DetalleConsulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalleC;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_consulta", nullable=false, foreignKey=@ForeignKey(name="FK_consulta_detalle"))
	private Consulta consulta;
	
	@Column(name = "diagnostico", nullable = false, length = 100)
	private String diagnostico;

	@Column(name = "tratamiento", nullable = false, length = 300)
	private String tratamiento;

	public Integer getIdDetalleC() {
		return idDetalleC;
	}

	public void setIdDetalleC(Integer idDetalleC) {
		this.idDetalleC = idDetalleC;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	

}
