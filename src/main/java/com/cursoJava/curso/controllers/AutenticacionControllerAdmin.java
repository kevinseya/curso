package com.cursoJava.curso.controllers;
import com.cursoJava.curso.dao.usuarioAdminDao;
import com.cursoJava.curso.models.Usuario;
import com.cursoJava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacionControllerAdmin {

    @Autowired
    private usuarioAdminDao usuarioAdminDao;

    @Autowired
    private JWTUtil jwtUtil;


//este es para el usuarioADMIN
    @RequestMapping(value= "api/login", method = RequestMethod.POST)
    public String registrarUsuario(@RequestBody Usuario usuario){

        Usuario usuarioLogueado = usuarioAdminDao.obtenerUsuarioPorCredenciales(usuario);
        if(usuarioLogueado != null){

            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getIdusuarioadmin()), usuarioLogueado.getEmail());

            return tokenJwt+";"+usuarioLogueado.getIdusuarioadmin();
        }
        return "FAIL";
    }



}
