package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.models.Usuario;

import java.util.List;

public interface eventoDao {

    List<Evento> listarEventos();
    void registrarEvento(Evento evento);
    void eliminarEvento(long idevento);
    Evento encontrarEvento(long idevento);



}

