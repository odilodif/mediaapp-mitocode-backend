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
import com.mitocode.model.Especialidad;
import com.mitocode.service.IEspecialidadService;

/************************************
 * Manejo de Rutas
 **************************************************/

@RestController // Le vamos indicar a spring que este es un servicio RestController Rutas manejo
				// // de Rutas
@RequestMapping("/especialidades") // Un nombre en particular con anotacion RequesteMaping la ruta "/especialidads"
public class EspecialidadController {

	@Autowired
	private IEspecialidadService service;

	@GetMapping
	public ResponseEntity<List<Especialidad>> listar() {
		List<Especialidad> lista = service.lista();
		return new ResponseEntity<List<Especialidad>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Especialidad> listarPorId(@PathVariable("id") Integer id) {

		Especialidad obj = service.listarPorId(id);

		if (obj.getIdEspecialidad() == null) {
			throw new ModeloNotFoundExeption("ID NO ENCONTRADO: " + id);

		} else {

		}

		return new ResponseEntity<Especialidad>(obj, HttpStatus.OK);
	}

	
	@PostMapping
	public ResponseEntity<Especialidad> registrar(@RequestBody Especialidad obj) {

		Especialidad pac = service.registrar(obj);
		return new ResponseEntity<Especialidad>(pac, HttpStatus.CREATED);
	}

	
	/*@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Especialidad obj) {
		
		Especialidad pac = service.registrar(obj);
		//localhost:8080/especialidads/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pac.getIdEspecialidad()).toUri();
		return ResponseEntity.created(location).build();
	}
	*/
	
	
	
	@PutMapping
	public ResponseEntity<Especialidad> modificar(@RequestBody Especialidad obj) {

		Especialidad especialidad = service.modificar(obj);
		return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) { // Retorna Vacio al poner Object
																				// //ResponseEntity<Object>
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
