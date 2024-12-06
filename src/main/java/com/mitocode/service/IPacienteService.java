package com.mitocode.service;

import java.util.List;

import com.mitocode.model.Paciente;

public interface IPacienteService extends ICRUD<Paciente, Integer> { // Se aplica que una Interfaz puede heredar de varias Interfaces pero no puede implementar de otra Interfaz
	//Se comenta porque se hace una heredacion a la Interfaz ICRUD 
	
	/*Paciente registrar(Paciente pac);
	Paciente modificar(Paciente pac);
	List<Paciente> lista();
	Paciente listarPorId(Integer id);
	void eliminar(Integer id);*/
}
