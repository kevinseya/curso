package com.cursoJava.curso.servicios;

import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.Ticket;
import jdk.jfr.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ticketServicio {

    Optional<Ticket> obtenerDetallesTicket (Long id);
    List<Ticket> encontrarPorEvento(long idevento);
    boolean ComprarBoletos(long idticket, long idevento, long idusuariocliente, int cantidad, double costototal, LocalDate fecha, String formaPago);
}
