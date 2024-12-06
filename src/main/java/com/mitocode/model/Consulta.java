package com.mitocode.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity // Va a realizar JPA Persistencia a la Base de Datos
@Table(name = "consulta") // Como se va a ver en la base de datos
public class Consulta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsulta;

	// JPQL
	
	/* De varios escoges una clave Foreanea*/
	@ManyToOne
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_medico"))
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_esxpecialidad"))
	private Especialidad especialidad;
	


	@Column(name = "num_consultorio", nullable = true, length = 3)
	private String num_consultorio;

	@Column(name = "fecha")
	private LocalDateTime fecha;

	/**Maestro- Detalle  o Cabecera Detalle  Aqui en cambio de clave despliega el Detalle en esta caso Tienes la Consulta con us Respectivo Detalle*/
	@OneToMany(mappedBy ="consulta", cascade= {CascadeType.ALL}, orphanRemoval = true)
	private List<DetalleConsulta> detalleConsulta;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConsulta == null) ? 0 : idConsulta.hashCode());
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
		Consulta other = (Consulta) obj;
		if (idConsulta == null) {
			if (other.idConsulta != null)
				return false;
		} else if (!idConsulta.equals(other.idConsulta))
			return false;
		return true;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	


	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getNum_consultorio() {
		return num_consultorio;
	}

	public void setNum_consultorio(String num_consultorio) {
		this.num_consultorio = num_consultorio;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public List<DetalleConsulta> getDetalleConsulta() {
		return detalleConsulta;
	}

	public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
		this.detalleConsulta = detalleConsulta;
	}
	
	//1
	//2
	
	

}
