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
import com.mitocode.model.Examen;
import com.mitocode.service.IExamenService;

/************************************
 * Rutas
 **************************************************/

@RestController // Le vamos indicar a spring que este es un servicio RestController Rutas manejo
				// // de Rutas
@RequestMapping("/examenes") // Un nombre en particular con anotacion RequesteMaping la ruta "/examens"
public class ExamenController {

	@Autowired
	private IExamenService service;

	@GetMapping
	public ResponseEntity<List<Examen>> listar() {
		List<Examen> lista = service.lista();
		return new ResponseEntity<List<Examen>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Examen> listarPorId(@PathVariable("id") Integer id) {

		Examen obj = service.listarPorId(id);

		if (obj.getIdExamen() == null) {
			throw new ModeloNotFoundExeption("ID NO ENCONTRADO: " + id);

		} else {

		}

		return new ResponseEntity<Examen>(obj, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<Examen> registrar(@RequestBody Examen obj) {

		Examen pac = service.registrar(obj);
		return new ResponseEntity<Examen>(pac, HttpStatus.CREATED);
	}

	
	/*@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Examen obj) {
		
		Examen pac = service.registrar(obj);
		//localhost:8080/examens/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdExamen()).toUri();
		return ResponseEntity.created(location).build();
	}*/
	
	
	
	@PutMapping
	public ResponseEntity<Examen> modificar(@RequestBody Examen obj) {

		Examen examen = service.modificar(obj);
		return new ResponseEntity<Examen>(examen, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) { // Retorna Vacio al poner Object
																				// //ResponseEntity<Object>
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
