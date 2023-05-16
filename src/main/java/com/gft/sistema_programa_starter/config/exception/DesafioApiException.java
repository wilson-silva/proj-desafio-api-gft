package com.gft.sistema_programa_starter.config.exception;


import lombok.Data;

@Data
public class DesafioApiException extends RuntimeException {
	
	private String message;

	public DesafioApiException(String message){
		super(message);
		this.message = message;
	}
}
