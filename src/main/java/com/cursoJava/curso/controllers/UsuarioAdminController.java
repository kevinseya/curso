package com.cursoJava.curso.controllers;
import com.cursoJava.curso.dao.usuarioAdminDao;
import com.cursoJava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import de.mkammerer.argon2.*;
import java.util.List;

@RestController
public class UsuarioAdminController {

    @Autowired
    private usuarioAdminDao usuarioAdminDao;


    @RequestMapping(value= "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(){

        return usuarioAdminDao.getUsuarios();
    }


    @RequestMapping(value= "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        String hash = argon2.hash(1, 1024, 1,usuario.getPassword());
        usuario.setPassword(hash);

        usuarioAdminDao.registrar(usuario);
    }

    @RequestMapping(value= "api/usuarios/{idusuarioadmin}", method = RequestMethod.DELETE)
    public void eliminarUsuario( @PathVariable Long idusuarioadmin){

        usuarioAdminDao.eliminar(idusuarioadmin);
    }



}
