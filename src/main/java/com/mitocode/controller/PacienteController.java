package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;

/************************************
 * Rutas
 **************************************************/

@RestController // Indicamos a spring que este es un servicio RestController Rutas manejo de
				// Rutas
@RequestMapping("/pacientes") // Definimos la ruta sustantivo en plural, con anotacion RequesteMaping la ruta
								// "/pacientes"
public class PacienteController {

	@Autowired // Se instancia con el Autowired IPacienteService = new PacienteServiceImp()
				// como se dejo preparado en PacienteServiceImp devido a que esta fue
				// implemetada de la Interfaz IPacienteService ero no se escribe NEW
	private IPacienteService service; // Se instancia con el Autowired IPacienteService = new PacienteServiceImp()
										// como se dejo preparado en PacienteServiceImp devido a que esta fue
										// implemetada de la Interfaz IPacienteService pero no se escribe NEW

	@GetMapping
	public ResponseEntity<List<Paciente>> listar() {
		List<Paciente> lista = service.lista();
		return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
	}

	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Paciente>> listarPageable(Pageable pageable) {
		Page<Paciente> pacientes = service.listarPageable(pageable);
		return new ResponseEntity<Page<Paciente>>(pacientes, HttpStatus.OK);

	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> listarPorId(@PathVariable("id") Integer id) {

		Paciente obj = service.listarPorId(id);

		if (obj.getIdPaciente() == null) {
			throw new ModeloNotFoundExeption("ID NO ENCONTRADO: " + id);

		} else {

		}

		return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Paciente> registrar(@RequestBody Paciente obj) {

		Paciente pac = service.registrar(obj);
		return new ResponseEntity<Paciente>(pac, HttpStatus.CREATED);
	}

	/*
	 * @PostMapping public ResponseEntity<Object> registrar(@RequestBody Paciente
	 * obj) {
	 * 
	 * Paciente pac = service.registrar(obj); //localhost:8080/pacientes/5 URI
	 * location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (pac.getIdPaciente()).toUri(); return
	 * ResponseEntity.created(location).build(); }
	 */

	@PutMapping
	public ResponseEntity<Paciente> modificar(@RequestBody Paciente obj) {

		Paciente paciente = service.modificar(obj);
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) { // Retorna Vacio al poner Object
																				// //ResponseEntity<Object>
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}
}
