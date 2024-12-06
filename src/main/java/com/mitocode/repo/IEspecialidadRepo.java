package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Especialidad;

//@Repository aqui realiza la persistencia con JPA
public interface IEspecialidadRepo extends JpaRepository<Especialidad, Integer> {
	
}
