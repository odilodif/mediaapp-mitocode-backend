package com.mitocode.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.exception.ModeloNotFoundExeption;
import com.mitocode.model.Archivo;
import com.mitocode.model.Consulta;
import com.mitocode.service.IArchivoService;
import com.mitocode.service.IConsultaService;
import org.apache.commons.codec.binary.Base64;
import net.sf.jasperreports.engine.JRException;

/************************************
 * * MANEJO DE RUTAS
 **************************************************/

@RestController // Le vamos indicar a spring que este es un servicio RestController Manejo de
				// Rutas
				// // de Rutas
@RequestMapping("/consultas") // Un nombre en particular con anotacion RequesteMaping la ruta "/consultas"
public class ConsultaController {

	@Autowired
	private IConsultaService service;

	@Autowired
	private IArchivoService serviceArchivo;

	@GetMapping
	public ResponseEntity<List<Consulta>> listar() {
		List<Consulta> lista = service.lista();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id) {

		Consulta obj = service.listarPorId(id);

		if (obj.getIdConsulta() == null) {
			throw new ModeloNotFoundExeption("ID NO ENCONTRADO: " + id);

		} else {

		}

		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}

	/*
	 * @PostMapping public ResponseEntity<Consulta> registrar(@RequestBody Consulta
	 * obj) {
	 * 
	 * Consulta pac = service.registrar(obj); return new
	 * ResponseEntity<Consulta>(pac, HttpStatus.CREATED); }
	 */

	/*
	 * @PostMapping public ResponseEntity<Object> registrar(@RequestBody Consulta
	 * obj) {
	 * 
	 * Consulta pac = service.registrar(obj); //localhost:8080/consultas/5 URI
	 * location =
	 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
	 * (pac.getIdConsulta()).toUri(); return
	 * ResponseEntity.created(location).build(); }
	 */

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody ConsultaListaExamenDTO dto) {

		Consulta pac = service.registrarTransaccional(dto);
		// localhost:8080/consultas/5
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(pac.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Consulta> modificar(@RequestBody Consulta obj) {

		Consulta consulta = service.modificar(obj);
		return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) { // Retorna Vacio al poner Object
																				// //ResponseEntity<Object>
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/buscar")
	public ResponseEntity<List<Consulta>> buscar(@RequestBody FiltroConsultaDTO filtro) {
		List<Consulta> consultas = new ArrayList<>();
		if (filtro != null) {
			if (filtro.getFechaConsulta() != null) {
				consultas = service.buscarFecha(filtro);
			} else {
				consultas = service.buscar(filtro);
			}
		}

		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}

	@GetMapping(value = "/listarResumen")
	public ResponseEntity<List<ConsultaResumenDTO>> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		consultas = service.listarResumen();
		return new ResponseEntity<List<ConsultaResumenDTO>>(consultas, HttpStatus.OK);

	}

	@GetMapping(value = "/generarReporte", produces = "application/octet-stream")
	public ResponseEntity<byte[]> generarReporte() {
		byte[] data = null;
		data = service.generarReporte();

		String fileName = "reporte-personalizado.pdf";

		// Retornar la respuesta con los datos, encabezados y estado HTTP
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(data);
	}

	@GetMapping(value = "/generarReportePdfViewer", produces = "application/pdf")
	public ResponseEntity<ByteArrayResource> generarReportePdfViewer() {
		byte[] data = null;
		data = service.generarReportePdfViewer();
		try {
			ByteArrayResource resource = new ByteArrayResource(data);
			return ResponseEntity.ok().header("Content-Disposition", "inline; filename=reporte.pdf") // "inline" para
																										// que se pueda
																										// visualizar en
																										// el navegador
					.header("Content-Type", "application/pdf").body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}

	}

	@PostMapping(value = "/guardarArchivo", consumes = { org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Integer> guardarArchivo(@RequestParam("adjunto") MultipartFile file) throws IOException {
		int rpta = 0;

		Archivo ar = new Archivo();
		ar.setFiletype(file.getContentType());
		ar.setFilename(file.getName());
		ar.setValue(file.getBytes());

		rpta = serviceArchivo.guardar(ar);

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}

	@GetMapping(value = "/leerArchivo/{idArchivo}", produces = org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<String> leerArchivo(@PathVariable("idArchivo") Integer idArchivo) throws IOException {

		byte[] arr = serviceArchivo.leerArchivo(idArchivo);
		String base64Encoded = Base64.encodeBase64String(arr);
		//System.out.println("Base64: "+ base64Encoded);
		//return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
		 return ResponseEntity.ok(base64Encoded);
	}

}
