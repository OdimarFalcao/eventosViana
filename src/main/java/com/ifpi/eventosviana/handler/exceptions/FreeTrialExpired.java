package com.ifpi.eventosviana.handler.exceptions;

public class FreeTrialExpired extends RuntimeException{

    public FreeTrialExpired(String message){
        super(message);
    }
}