package com.cursoJava.curso.servicios;

import com.cursoJava.curso.models.Ticket;

public interface ticketServicio {

    void registrarNuevoTicket (Ticket ticket);
    Ticket obtenerDetallesTicket (Ticket ticket);
    void actualizarDisponibilidadTicket (Ticket ticketActualizado, long idticket, int nuevaDisponibilidad );
}
