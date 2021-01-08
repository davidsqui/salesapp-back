package com.dasc.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApiException(String mensaje) {
		super(mensaje);
	}
}
