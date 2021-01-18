package com.demigods.ordergeneratorservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RecordNotFoundException extends ResponseStatusException {
	private static final long serialVersionUID = -7461253909308359551L;
	
	public RecordNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }
    public RecordNotFoundException(String message, Throwable cause) {
        super(HttpStatus.NOT_FOUND, message, cause);
    }
    public RecordNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
