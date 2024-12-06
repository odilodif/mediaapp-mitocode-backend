package com.mitocode.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.ConsultaExamen;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.service.IConsultaExamenService;

@Service
public class ConsultaExamenServiceImp implements IConsultaExamenService {

	@Autowired
	private IConsultaExamenRepo repo; 													//Acceso a la base de Datos mediante @Autowired trae una instanacia // new  IExamenRepo*

	@Override
	public List<ConsultaExamen> listarExamenesPorConsulta(Integer idConsulta) {    		//Implementacion de metodo de la Intefax IConsultaExamenService

		return repo.listarExamenesPorConsulta(idConsulta);
	}

}
