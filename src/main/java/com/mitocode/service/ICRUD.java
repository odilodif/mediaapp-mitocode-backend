package com.mitocode.service;

import java.util.List;

public interface ICRUD<T, V> {

	T registrar (T obj);
	T modificar (T obj);
	List<T> lista();
	T listarPorId(V id);
	boolean eliminar(V id);
}
