package com.gft.sistema_programa_starter.config.exception;


import com.gft.sistema_programa_starter.dto.error.ApiErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler( { DesafioApiException.class } )
	public ResponseEntity<ApiErrorDTO> handDesafioApiException(DesafioApiException ex, WebRequest request){
		String error = "Erro no sistema";
		ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
		
	}

	//--------------------------------------------------------------------
	@ExceptionHandler( { EntityNotFoundException.class } )
	public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
		String error = "Recurso não encontrado";
		ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	//--------------------------------------------------------------------
	@ExceptionHandler( { ConstraintViolationException.class } )
	public ResponseEntity<ApiErrorDTO> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		String error = "Dado inválido";
		ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(), error, HttpStatus.BAD_REQUEST);
		return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
	}

}
