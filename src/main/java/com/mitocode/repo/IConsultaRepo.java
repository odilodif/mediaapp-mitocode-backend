package com.mitocode.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Consulta;


public interface IConsultaRepo extends  JpaRepository<Consulta, Integer> {
	
	@Query("from Consulta c where c.paciente.dni=:dni or LOWER(c.paciente.nombres) like %:nombreCompleto% or LOWER(c.paciente.apellidos) like %:nombreCompleto%")
	List<Consulta> buscar(@Param("dni")String dni, @Param("nombreCompleto") String nombreCompleto);
	
	@Query("from Consulta c where c.fecha  between :fechaConsulta and :fechaDiaSiguiente")
	List<Consulta> buscarFecha(@Param("fechaConsulta") LocalDateTime fechaConsulta, @Param("fechaDiaSiguiente") LocalDateTime fechaDiaSiguiente);
	
	@Query(value = "SELECT * FROM \"public\".\"fn_listarresumen\"()", nativeQuery = true)
	List<Object[]> listarResumen();

}
