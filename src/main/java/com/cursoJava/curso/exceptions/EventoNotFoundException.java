package com.cursoJava.curso.exceptions;

public class EventoNotFoundException extends RuntimeException{

    public EventoNotFoundException() {
        super("Evento no encontrado.");
    }
}
