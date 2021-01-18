package com.demigods.ordergeneratorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AlreadyRegisteredException extends ResponseStatusException{

	private static final long serialVersionUID = 4043021577010143386L;
	
	public AlreadyRegisteredException() {
        super(HttpStatus.METHOD_NOT_ALLOWED);
    }
	public AlreadyRegisteredException(String message) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message);
    }
    public AlreadyRegisteredException(String message, Throwable cause) {
        super(HttpStatus.METHOD_NOT_ALLOWED, message, cause);
    }
}
