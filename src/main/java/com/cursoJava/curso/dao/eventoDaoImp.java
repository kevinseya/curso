package com.cursoJava.curso.dao;


import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.UsuarioCliente;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class eventoDaoImp implements eventoDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Evento> getEventos() {

        String query = "FROM Evento";
        return entityManager.createQuery(query).getResultList();
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





}
