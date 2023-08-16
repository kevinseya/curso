package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.Ticket;
import com.cursoJava.curso.models.DetalleCompra;
import com.cursoJava.curso.models.UsuarioCliente;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ticketDaoImp implements ticketDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Ticket> listarTickets() {
        String query = "FROM Ticket";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public int disponibilidadPorEvento(long idevento) {
        String consulta = "SELECT e.cantboletosdisponibles FROM Evento e WHERE e.idevento=:idevento";
        Query query = entityManager.createQuery(consulta, Integer.class);
        query.setParameter("idevento", idevento);
        Integer disponibilidad = (Integer) query.getSingleResult();
        return disponibilidad != null ? disponibilidad : 0;

    }
    @Override
    public long crearCompra(long idticket, long idevento, long idusuariocliente, int cantidad, double costototal, LocalDate fecha, String formaPago){
        EntityTransaction transaction = entityManager.getTransaction();
        long iddetallecompra = -1;
        try{
            transaction.begin();
            DetalleCompra  detallecompra = new DetalleCompra();
            Evento evento = entityManager.find(Evento.class,idevento);
            detallecompra.setIdevento(idevento);
            UsuarioCliente usuariocliente = entityManager.find(UsuarioCliente.class,idusuariocliente);
            detallecompra.setIdusuariocliente(idusuariocliente);
            Ticket ticket = entityManager.find(Ticket.class, idticket);
            detallecompra.setIdticket(idticket);
            detallecompra.setCantidad(cantidad);
            detallecompra.setCostototal(costototal);
            detallecompra.setFecha(fecha);
            detallecompra.setFormaPago(formaPago);
            entityManager.persist(detallecompra);
            transaction.commit();

            iddetallecompra = detallecompra.getIdetallecompra();

        }catch(Exception e){
            if(transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
        return iddetallecompra;

    }

    @Override
    public void actualizarDisponibilidad(long idevento, int nuevadisponibilidad) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Ticket ticket = entityManager.find(Ticket.class, idevento);
            if(ticket != null){
                ticket.setDisponibilidad(nuevadisponibilidad);
                entityManager.merge(ticket);
            }
            transaction.commit();
        }catch(Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
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
    public List<Ticket> buscarporEvento(long idevento) {
        String query = "SELECT t FROM Ticket t WHERE t.idevento=:idevento";
        return entityManager.createQuery(query,Ticket.class).setParameter("idevento", idevento).getResultList();
    }

    @Override
    public Optional<Ticket> encontrarTicket(long idticket) {

        return Optional.ofNullable(entityManager.find(Ticket.class, idticket));
    }

}
