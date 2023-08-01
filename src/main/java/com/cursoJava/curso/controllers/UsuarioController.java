package com.cursoJava.curso.controllers;
import com.cursoJava.curso.dao.usuarioDao;
import com.cursoJava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.mkammerer.argon2.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private usuarioDao usuarioDao;


    @RequestMapping(value= "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){

        return usuarioDao.getUsuarios();
    }


    @RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon2.hash(1, 1024, 1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value= "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario( @PathVariable Long id){

        usuarioDao.eliminar(id);
    }



}
