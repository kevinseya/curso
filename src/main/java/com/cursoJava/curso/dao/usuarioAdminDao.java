package com.cursoJava.curso.dao;


import com.cursoJava.curso.models.Usuario;

import java.util.List;

public interface usuarioAdminDao {
    List<Usuario> getUsuarios();

    void eliminar(Long idusuarioadmin);

    void registrar(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
