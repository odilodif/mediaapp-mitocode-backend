package com.mitocode.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Examen;
import com.mitocode.repo.IExamenRepo;
import com.mitocode.service.IExamenService;

@Service    // Le digo a spring que le gestione a esta clase como servicio para que utilice otra clase
public class ExamenServiceImp  implements IExamenService{

	@Autowired
	private IExamenRepo repo; //Acceso a la base de Datos mediante @Autowired trae una instanacia // new  IExamenRepo 
	@Override
	public Examen registrar(Examen pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public Examen modificar(Examen pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public List<Examen> lista() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Examen listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Examen>  op = repo.findById(id);
		return op.isPresent() ? op.get() : new Examen();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return true;
		
	}

}
