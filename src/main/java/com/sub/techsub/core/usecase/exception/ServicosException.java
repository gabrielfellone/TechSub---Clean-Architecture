package com.sub.techsub.core.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServicosException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public ServicosException(String message, Exception e) {super(message, e);}
    public ServicosException(String message) {super(message);}
}