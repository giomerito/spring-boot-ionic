package com.giomerito.cursomc.services.exceptions;

public class AuthorizationException extends RuntimeException{

	//Classe gerada para fazer a tratação de erros
	
	private static final long serialVersionUID = 1L;
	

	public AuthorizationException(String msg) {
		super(msg);
	}
	
	public AuthorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
