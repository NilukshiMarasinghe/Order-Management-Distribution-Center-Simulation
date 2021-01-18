package com.demigods.orderManagementService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrderExistsException extends ResponseStatusException {

    private static final long serialVersionUID = 1030597317912167194L;

    public OrderExistsException() {
        super(HttpStatus.BAD_REQUEST);
    }
    public OrderExistsException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
    public OrderExistsException(String message, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, message, cause);
    }

}
