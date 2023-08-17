package com.cursoJava.curso.servicios;

import com.cursoJava.curso.dao.ticketDao;
import com.cursoJava.curso.dao.eventoDao;
import com.cursoJava.curso.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ticketServicioImp implements ticketServicio{

    @Autowired
    ticketDao ticketDao;
    @Autowired
    eventoDao eventoDao;


    @Override
    public Optional<Ticket> obtenerDetallesTicket(Long id) {

        return ticketDao.encontrarTicket(id);
    }

    @Override
    public List<Ticket> encontrarPorEvento(long idevento) {
        return ticketDao.buscarporEvento(idevento);
    }

    public boolean ComprarBoletos(long idticket, long idevento, long idusuariocliente, int cantidad, double costototal, LocalDate fecha, String formaPago){

        int disponibilidadActual = ticketDao.disponibilidadPorEvento(idevento);
        if(disponibilidadActual >= cantidad){
            ticketDao.actualizarDisponibilidad(idevento, disponibilidadActual-cantidad);
            //logica ingreso datos
            long iddetallecompra = ticketDao.crearCompra(idticket, idevento, idusuariocliente, cantidad, costototal, fecha, formaPago);
            if (iddetallecompra != 1){
                return true; //compra exitosa
            }
        }
            return false; //no hay suficiente disponibilidad
    }

}
