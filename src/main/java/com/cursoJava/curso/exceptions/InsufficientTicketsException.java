package com.cursoJava.curso.exceptions;

public class InsufficientTicketsException extends RuntimeException {
    public InsufficientTicketsException() {
        super("No hay suficientes boletos disponibles.");
    }

    public InsufficientTicketsException(String message) {
        super(message);
    }

}
