package com.transport.app.platform.check.domain.exceptions;

public class RequestNotFoundException extends RuntimeException{
    public RequestNotFoundException(Long aLong) {
        super("Request with id " + aLong + " not found");
    }
}
