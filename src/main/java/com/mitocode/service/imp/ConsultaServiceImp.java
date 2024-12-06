package com.mitocode.service.imp;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.mitocode.dto.ConsultaListaExamenDTO;
import com.mitocode.dto.ConsultaResumenDTO;
import com.mitocode.dto.FiltroConsultaDTO;
import com.mitocode.model.Consulta;
import com.mitocode.repo.IConsultaExamenRepo;
import com.mitocode.repo.IConsultaRepo;
import com.mitocode.service.IConsultaService;

import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service // Le digo a spring que le gestione a esta clase como servicio para que utilice
			// otra clase
public class ConsultaServiceImp implements IConsultaService {

	@Autowired
	private IConsultaRepo repo; // Repositorio -> Acceso a la base de Datos JPA mediante @Autowired trae una
								// instanacia // new IConsultaRepo

	@Autowired
	private IConsultaExamenRepo ceRepo;

	@Override
	public Consulta registrar(Consulta cons) {
		cons.getDetalleConsulta().forEach(det -> {
			det.setConsulta(cons);
		});
		return repo.save(cons);
	}

	@Transactional
	@Override
	public Consulta registrarTransaccional(ConsultaListaExamenDTO dto) {
		dto.getConsulta().getDetalleConsulta().forEach(det -> {
			det.setConsulta(dto.getConsulta());
		});
		repo.save(dto.getConsulta());
		dto.getLstExamen().forEach(exa -> ceRepo.registrar(dto.getConsulta().getIdConsulta(), exa.getIdExamen()));
		return dto.getConsulta();

	}

	@Override
	public Consulta modificar(Consulta cons) {
		// TODO Auto-generated method stub
		return repo.save(cons);
	}

	@Override
	public List<Consulta> lista() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Consulta listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Consulta> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Consulta();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return true;

	}

	@Override
	public List<Consulta> buscar(FiltroConsultaDTO filtro) {
		return repo.buscar(filtro.getDni(), filtro.getNombreCompleto());
	}

	@Override
	public List<Consulta> buscarFecha(FiltroConsultaDTO filtro) {

		LocalDateTime fechaDiaSiguiente = filtro.getFechaConsulta().plusDays(1);
		return repo.buscarFecha(filtro.getFechaConsulta(), fechaDiaSiguiente);
	}

	@Override
	public List<ConsultaResumenDTO> listarResumen() {
		List<ConsultaResumenDTO> consultas = new ArrayList<>();
		
		//cantidad    		fecha
		//[2			,		04/02/2020]
		//[1			,		06/02/2020]
		//[6			,		10/03/2019]          Por retornar un Object se llega con el ForEach hasta el Array Objeto y se castea a Integer x[0] y a String x[1]  
		repo.listarResumen().forEach(x -> {
			ConsultaResumenDTO cr = new ConsultaResumenDTO();			
			cr.setCantidad( Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		return consultas;
	}

	
	@Override
	public byte[] generarReporte() { 												//Metodo para la generacion de Reporte PDF desde un archivo *.jasper
		byte[] data = null;
		/* HashMap<String, String> params = new HashMap<String,String>();    		//Para cuando el JasperRepor venga con parametros
		 params.put("txt_empresa", "Motocode Network");*/
		 
		try {
			File file = new ClassPathResource("/reports/consultas.jasper").getFile();
			System.out.println("PATH: "+file.getPath());
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		} catch (IOException e) {
	        System.err.println("Error al cargar el archivo jasper: " + e.getMessage());
	    } catch (JRException e) {
	        System.err.println("Error al generar el reporte: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return data;
	}

	@Override
	public byte[] generarReportePdfViewer() {	 

		
		 byte[] data = null;
			/* HashMap<String, String> params = new HashMap<String,String>();    		//Para cuando el JasperRepor venga con parametros
			 params.put("txt_empresa", "Motocode Network");*/
			 
		 try {
		        File file = new ClassPathResource("/reports/consultas.jasper").getFile();
		        System.out.println("PATHPdfViewer: " + file.getPath());
		        JasperPrint print = JasperFillManager.fillReport(file.getPath(), null, new JRBeanCollectionDataSource(this.listarResumen()));
		        data = JasperExportManager.exportReportToPdf(print);

		        
		 } catch (IOException e) {
		        System.err.println("Error al cargar el archivo jasper: " + e.getMessage());
		    } catch (JRException e) {
		        System.err.println("Error al generar el reporte: " + e.getMessage());
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
			
			return data;
		  
	}

}
