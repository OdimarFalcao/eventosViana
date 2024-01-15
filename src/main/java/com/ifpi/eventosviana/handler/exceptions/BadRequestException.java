package com.ifpi.eventosviana.handler.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }
}
