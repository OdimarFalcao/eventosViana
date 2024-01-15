package com.ifpi.eventosviana.handler.exceptions;

import java.util.function.Supplier;

public class NotFound extends RuntimeException implements Supplier<NotFound> {
    private String mensagem;
    public NotFound(String message){
        super(message);
        this.mensagem = message;
    }

    @Override
    public NotFound get() {
        return new NotFound(mensagem);
    }
}
