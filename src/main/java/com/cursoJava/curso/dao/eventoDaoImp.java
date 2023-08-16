package com.cursoJava.curso.dao;


import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.UsuarioCliente;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class eventoDaoImp implements eventoDao{

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Evento>listarEventos(String genero){
        String query = "SELECT e FROM Evento e WHERE e.genero=:genero";
        List<Evento> eventos = entityManager.createQuery(query, Evento.class).setParameter("genero",genero).getResultList();
        return eventos;
    }

    @Override
    public List<Evento> listarEventosPorGenero(String genero) {
        String query = "SELECT e FROM Evento e WHERE e.genero=:genero";
        TypedQuery<Evento> lista = entityManager.createQuery(query, Evento.class);
        lista.setParameter("genero", genero);
        return lista.getResultList();
    }

    @Override
    public List<Evento> listarEventosporId(Long idusuarioadmin) {
        String query = "SELECT e FROM Evento e WHERE e.idusuarioadmin=:idusuarioadmin";
        TypedQuery<Evento> lista = entityManager.createQuery(query, Evento.class);
        lista.setParameter("idusuarioadmin", idusuarioadmin);
        return lista.getResultList();
    }

    @Override
    public void registrarEvento(Evento evento) {
        entityManager.merge(evento);

    }


    @Override
    public void eliminarEvento(long idevento) {
        Evento evento = entityManager.find(Evento.class , idevento);
        entityManager.remove(evento);
    }

    @Override
    public Evento encontrarEvento(long idevento){
        return entityManager.find(Evento.class, idevento);
    }

    @Override
    public List<Evento> buscarEventoPorNombre(String nombre) {
        String query = "SELECT e FROM Evento e WHERE e.nombre LIKE CONCAT('%', :nombre, '%')";
        List<Evento> eventosBusquedaNombre = entityManager.createQuery(query, Evento.class).setParameter("nombre", nombre).getResultList();
        return eventosBusquedaNombre;
    }
    @Override
    public List<Evento> getEventos() {
        String query = "FROM Evento ";
        return entityManager.createQuery(query).getResultList();
    }




}
