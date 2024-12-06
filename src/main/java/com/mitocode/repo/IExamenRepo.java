package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Examen;

//@Repository aqui realiza la persistencia con JPA
public interface IExamenRepo extends JpaRepository<Examen, Integer> {
	
}
