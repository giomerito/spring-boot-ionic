package com.giomerito.cursomc.services.exceptions;

public class DataIntegrityException extends RuntimeException{

	//Classe gerada para fazer a tratação de erros
	
	private static final long serialVersionUID = 1L;
	

	public DataIntegrityException(String msg) {
		super(msg);
	}
	
	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
