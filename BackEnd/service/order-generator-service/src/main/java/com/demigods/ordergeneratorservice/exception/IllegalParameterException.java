package com.demigods.ordergeneratorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IllegalParameterException extends ResponseStatusException {

	private static final long serialVersionUID = 1030597317912167194L;
	
	public IllegalParameterException() {
        super(HttpStatus.BAD_REQUEST);
    }
	public IllegalParameterException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
    public IllegalParameterException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, message, cause);
    }

}
