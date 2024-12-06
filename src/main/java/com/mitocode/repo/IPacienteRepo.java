package com.mitocode.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mitocode.model.Paciente;

//@Repository aqui realiza la persistencia con JPA
public interface IPacienteRepo extends JpaRepository<Paciente, Integer> { 			//Hereda de otra interfaz JpaRepository que tiene los metodos CRUD y Otros Metodos(paginacion) mas esta mas arriba de la CRUDRepository 
																					//y tienes que indicarle como parametros que Entidad y el tipo de la clave Primary. En este caso <Paciente, Integer>
																					
}
