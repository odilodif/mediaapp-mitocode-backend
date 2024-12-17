package com.mitocode.service;

import com.mitocode.model.Archivo;

public interface IArchivoService {

	int guardar(Archivo idArchivo);
	byte[] leerArchivo(Integer idArchivo);
	
}
