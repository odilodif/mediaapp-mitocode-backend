package com.mitocode.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.model.Paciente;
import com.mitocode.repo.IPacienteRepo;
import com.mitocode.service.IPacienteService;

@Service    													// Con el @Service le digo a Spring que le gestione a esta clase como servicio para que utilice otra clase
public class PacienteServiceImp  implements IPacienteService{   //Implementa la Interfaz IPacienteService que tiene los metodos CRUD y despues sera instanaciada como -> " IPacienteService = new PacienteServiceImp();  " 

	@Autowired
	private IPacienteRepo repo; 								//Acceso a la base de Datos mediante @Autowired trae una instanacia // new  IPacienteRepo 
	@Override
	public Paciente registrar(Paciente pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public Paciente modificar(Paciente pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public List<Paciente> lista() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Paciente listarPorId(Integer id) {
		// objeto Optional controla para que no retorne NULL
		Optional<Paciente>  op = repo.findById(id);
		return op.isPresent() ? op.get() : new Paciente();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return true;
		
	}

	@Override
	public Page<Paciente> listarPageable(Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(pageable);
	}

}
