package com.cursoJava.curso.controllers;

import com.cursoJava.curso.dao.ticketDao;
import com.cursoJava.curso.models.Ticket;
import com.cursoJava.curso.utils.JWTUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {
    @Autowired
    private ticketDao ticketdao;


    @RequestMapping(value="api/listarTickets",method = RequestMethod.GET)
    public List<Ticket> listarTickets(){

        return ticketdao.listarTickets();

    }

    @RequestMapping(value= "api/registroTickets", method = RequestMethod.POST)
    public void registrarTicket(@RequestBody Ticket ticket){
        ticketdao.registrarTicket(ticket);
    }
    @RequestMapping(value = "api/eliminarTickets", method = RequestMethod.DELETE)
    public void eliminarTicket (@RequestBody long idticket){
        ticketdao.eliminarTicket(idticket);
    }



}
