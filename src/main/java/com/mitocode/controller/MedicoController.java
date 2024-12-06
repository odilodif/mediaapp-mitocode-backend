package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundExeption;
import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;

/************************************
 * Rutas
 **************************************************/

@RestController // Le vamos indicar a spring que este es un servicio RestController Rutas manejo
				// // de Rutas
@RequestMapping("/medicos") // Un nombre en particular con anotacion RequesteMaping la ruta "/medicos"
public class MedicoController {

	@Autowired
	private IMedicoService service;

	@GetMapping
	public ResponseEntity<List<Medico>> listar() {
		List<Medico> lista = service.lista();
		return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> listarPorId(@PathVariable("id") Integer id) {

		Medico obj = service.listarPorId(id);

		if (obj.getIdMedico() == null) {
			throw new ModeloNotFoundExeption("ID NO ENCONTRADO: " + id);

		} else {

		}

		return new ResponseEntity<Medico>(obj, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<Medico> registrar(@RequestBody Medico obj) {

		Medico pac = service.registrar(obj);
		return new ResponseEntity<Medico>(pac, HttpStatus.CREATED);
	}

	/*
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Medico obj) {
		
		Medico pac = service.registrar(obj);
		//localhost:8080/medicos/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdMedico()).toUri();
		return ResponseEntity.created(location).build();
	}*/
	
	
	
	@PutMapping
	public ResponseEntity<Medico> modificar(@RequestBody Medico obj) {

		Medico medico = service.modificar(obj);
		return new ResponseEntity<Medico>(medico, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) { // Retorna Vacio al poner Object
																				// //ResponseEntity<Object>
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
