package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ModeloNotFoundExeption extends RuntimeException {

	public ModeloNotFoundExeption(String message) {
		super(message);
	}
}
