package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.Ticket;

import java.util.List;

public interface ticketDao {


    List<Evento> listarTickets();
    void registrarTicket(Ticket ticket);
    void eliminarTicket(long idticket);
    Ticket encontrarTicket(long idticket);
}
