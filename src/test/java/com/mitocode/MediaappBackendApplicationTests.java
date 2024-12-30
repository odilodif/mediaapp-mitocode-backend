package com.mitocode;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mitocode.model.Usuario;
import com.mitocode.repo.IUsuarioRepo;

@SpringBootTest
class MediaappBackendApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Autowired
	private IUsuarioRepo repo;

	@Test
	void crearUsuario() {
		Usuario us = new Usuario();
		us.setIdUsuario(2);
		us.setUsername("mitocode23@gmail.com");
		us.setPassword(bcrypt.encode("123"));
		us.setState_enabled("ACTIVO");

		Usuario retorno = repo.save(us);		

		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
		
	}

}
