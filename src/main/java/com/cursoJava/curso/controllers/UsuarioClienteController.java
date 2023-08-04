package com.cursoJava.curso.controllers;
import com.cursoJava.curso.dao.usuarioClienteDao;
import com.cursoJava.curso.models.UsuarioCliente;
import com.cursoJava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.mkammerer.argon2.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class UsuarioClienteController {

    @Autowired
    private usuarioClienteDao usuarioClienteDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value= "api/usuariosCliente", method = RequestMethod.GET)
    public List<UsuarioCliente> getUsuariosCliente(@RequestHeader(value= "Authorization")String token){

        String[] tokens = token.split(";");
        String tokenf = tokens[0];
        String id = tokens[1];
        String usuarioid = jwtUtil.getKey(tokenf);

        if(usuarioid == null ){
            return new ArrayList<>();
        }
        return usuarioClienteDao.getUsuariosCliente();
    }


    @RequestMapping(value= "api/usuariosCliente", method = RequestMethod.POST)
    public void registrarUsuarioCliente(@RequestBody UsuarioCliente usuariocliente){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon2.hash(1, 1024, 1,usuariocliente.getPassword());
        usuariocliente.setPassword(hash);

        usuarioClienteDao.registrarCliente(usuariocliente);
    }

    @RequestMapping(value= "api/usuariosCliente/{idusuariocliente}", method = RequestMethod.DELETE)
    public void eliminarUsuarioCliente( @PathVariable Long idusuariocliente){

        usuarioClienteDao.eliminarCliente(idusuariocliente);
    }



}
