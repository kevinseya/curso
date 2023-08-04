package com.cursoJava.curso.controllers;
import com.cursoJava.curso.models.Evento;
import com.cursoJava.curso.dao.eventoDao;
import com.cursoJava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class EventoController {

    @Autowired
    private eventoDao eventoDao;
    @Autowired
    private JWTUtil jwtUtil;



    @RequestMapping(value= "api/eventos", method = RequestMethod.POST)
    public void registrarEvento(@RequestBody Evento evento){

        eventoDao.registrarEvento(evento);
    }

    @RequestMapping(value= "api/eventos", method = RequestMethod.GET)
    public List<Evento> getEventos(@RequestHeader(value= "Authorization")String token){
        String[] tokens = token.split(";");
        String tokenf = tokens[0];
        String id = tokens[1];

        String usuarioid = jwtUtil.getKey(tokenf);

        if(usuarioid == null ){
            return new ArrayList<>();
        }

        return eventoDao.getEventos();
    }

    @RequestMapping(value= "api/eventos/{idevento}", method = RequestMethod.DELETE)
    public void eliminarUsuarioCliente( @PathVariable Long idevento){

        eventoDao.eliminarEvento(idevento);
    }









}
