package com.gft.sistema_programa_starter.dto.error;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.List;


@Data
public class ApiErrorDTO {
	
	private String message;
	private List<String> errors;
	private HttpStatus status;

	public ApiErrorDTO() {
	}

	public ApiErrorDTO(String message, List<String> errors, HttpStatus status) {
		this.message = message;
		this.errors = errors;
		this.status = status;
	}
	
	public ApiErrorDTO(String message, String error, HttpStatus status) {
		this.message = message;
		this.errors = Arrays.asList(error);
		this.status = status;
	}

}
