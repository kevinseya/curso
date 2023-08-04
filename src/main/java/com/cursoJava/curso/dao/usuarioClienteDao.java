package com.cursoJava.curso.dao;


import com.cursoJava.curso.models.UsuarioCliente;

import java.util.List;

public interface usuarioClienteDao {

    List<UsuarioCliente> getUsuariosCliente();

    void eliminarCliente(Long idusuariocliente);

    void registrarCliente(UsuarioCliente usuariocliente);


    UsuarioCliente obtenerUsuarioCPorCredenciales(UsuarioCliente usuariocliente);
}
