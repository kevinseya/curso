package com.cursoJava.curso.dao;


import com.cursoJava.curso.models.Usuario;

import java.util.List;

public interface usuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);


    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
