package com.cursoJava.curso.servicios;

import com.cursoJava.curso.dao.ticketDao;
import com.cursoJava.curso.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ticketServicioImp implements ticketServicio{

    @Autowired
    ticketDao ticketDao;

    @Override
    public void registrarNuevoTicket(Ticket ticket) {
        ticketDao.registrarTicket(ticket);
    }

    @Override
    public Ticket obtenerDetallesTicket(Ticket ticket) {
        return ticketDao.encontrarTicket(ticket.getIdticket());
    }

    @Override
    public void actualizarDisponibilidadTicket(Ticket ticketActualizado,long idticket, int nuevaDisponibilidad) {
        Ticket ticket = ticketDao.encontrarTicket(idticket);
        if(ticket != null){
            ticket.setDisponibilidad(nuevaDisponibilidad);
            ticket.setPrecioticket(ticketActualizado.getPrecioticket());
            ticketDao.registrarTicket(ticketActualizado);
        }else{
            System.out.println("Ticket no encontrado con ID: " +idticket);
        }
    }
}
