package com.mitocode.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Medico;
import com.mitocode.repo.IMedicoRepo;
import com.mitocode.service.IMedicoService;
import com.mitocode.service.IMedicoService;

@Service    // Le digo a spring que le gestione a esta clase como servicio para que utilice otra clase
public class MedicoServiceImp  implements IMedicoService{

	@Autowired
	private IMedicoRepo repo; //Acceso a la base de Datos JPA mediante @Autowired trae una instanacia // new  IMedicoRepo 
	@Override
	public Medico registrar(Medico pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public Medico modificar(Medico pac) {
		// TODO Auto-generated method stub
		return repo.save(pac);
	}

	@Override
	public List<Medico> lista() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Medico listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Medico>  op = repo.findById(id);
		return op.isPresent() ? op.get() : new Medico();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return true;
		
	}

}
