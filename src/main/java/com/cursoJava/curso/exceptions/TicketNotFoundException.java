package com.cursoJava.curso.exceptions;

public class TicketNotFoundException extends RuntimeException{

    public TicketNotFoundException(String mensaje) {
        super(mensaje);
    }
}
