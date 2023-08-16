package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ticketDao {


    List<Ticket> listarTickets();
    int disponibilidadPorEvento (long idevento);
    void actualizarDisponibilidad(long idevento, int nuevadisponibilidad);
    long crearCompra(long idticket, long idevento, long idusuariocliente, int cantidad, double costototal, LocalDate fecha, String formaPago);
    void registrarTicket(Ticket ticket);
    void eliminarTicket(long idticket);
    List<Ticket> buscarporEvento(long idevento);
    Optional<Ticket> encontrarTicket(long idticket);

}
