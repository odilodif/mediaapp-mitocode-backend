package com.mitocode.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Especialidad;
import com.mitocode.repo.IEspecialidadRepo;
import com.mitocode.service.IEspecialidadService;

@Service    // Le digo a spring que le gestione a esta clase como servicio para que utilice otra clase
public class EspecialidadServiceImp  implements IEspecialidadService{

	@Autowired
	private IEspecialidadRepo repo; //Acceso a la base de Datos mediante @Autowired trae una instanacia // new  IEspecialidadRepo 
	@Override
	public Especialidad registrar(Especialidad pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public Especialidad modificar(Especialidad pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public List<Especialidad> lista() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Especialidad listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Especialidad>  op = repo.findById(id);
		return op.isPresent() ? op.get() : new Especialidad();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return true;
		
	}

}
