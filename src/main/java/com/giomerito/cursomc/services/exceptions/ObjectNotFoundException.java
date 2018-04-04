package com.giomerito.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{

	//Classe gerada para fazer a tratação de erros
	
	private static final long serialVersionUID = 1L;
	

	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
