package com.giomerito.cursomc.services.exceptions;

public class FileException extends RuntimeException{

	//Classe gerada para fazer a tratação de erros
	
	private static final long serialVersionUID = 1L;
	

	public FileException(String msg) {
		super(msg);
	}
	
	public FileException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
