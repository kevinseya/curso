package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.Ticket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class ticketDaoImp implements ticketDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Evento> listarTickets() {
        String query = "FROM Ticket";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void registrarTicket(Ticket ticket) {
        entityManager.merge(ticket);
    }

    @Override
    public void eliminarTicket(long idticket) {
        Ticket ticket = entityManager.find(Ticket.class , idticket);
        entityManager.remove(ticket);
    }

    @Override
    public Ticket encontrarTicket(long idticket) {
        return entityManager.find(Ticket.class, idticket);
    }
}
