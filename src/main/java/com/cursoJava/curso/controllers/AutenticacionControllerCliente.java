package com.cursoJava.curso.controllers;
import com.cursoJava.curso.dao.usuarioClienteDao;
import com.cursoJava.curso.models.UsuarioCliente;
import com.cursoJava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacionControllerCliente {

    @Autowired
    private usuarioClienteDao usuarioClienteDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value= "api/loginCliente", method = RequestMethod.POST)
    public String registrarUsuarioCliente(@RequestBody UsuarioCliente usuariocliente){

        UsuarioCliente usuarioLogueadoCliente = usuarioClienteDao.obtenerUsuarioCPorCredenciales(usuariocliente);
        if(usuarioLogueadoCliente != null){

            String tokenJwtCliente = jwtUtil.create(String.valueOf(usuarioLogueadoCliente.getIdusuariocliente()), usuarioLogueadoCliente.getEmail());

            return tokenJwtCliente;
        }
        return "FAIL";
    }
}
