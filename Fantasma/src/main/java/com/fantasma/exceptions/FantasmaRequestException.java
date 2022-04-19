package com.fantasma.exceptions;

import org.springframework.http.HttpStatus;

public class FantasmaRequestException extends RuntimeException{
    private final String code;
    private final HttpStatus status;

    public FantasmaRequestException(String message, String code, HttpStatus status) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
