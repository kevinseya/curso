package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.UsuarioCliente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import de.mkammerer.argon2.*;

@Repository
@Transactional
public class UsuarioClienteDaoImp implements usuarioClienteDao {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<UsuarioCliente> getUsuariosCliente() {
        String query = "FROM UsuarioCliente ";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void eliminarCliente(Long idusuariocliente) {
        UsuarioCliente usuariocliente = entityManager.find(UsuarioCliente.class , idusuariocliente);
        entityManager.remove(usuariocliente);
    }

    @Override
    public void registrarCliente(UsuarioCliente usuariocliente) {
        entityManager.merge(usuariocliente);
    }

    @Override
    public UsuarioCliente obtenerUsuarioCPorCredenciales(UsuarioCliente usuariocliente) {

        String query = "FROM UsuarioCliente WHERE email =:email";
        List<UsuarioCliente> lista = entityManager.createQuery(query)
                .setParameter("email", usuariocliente.getEmail())
                .getResultList();

        if(lista.isEmpty()){
            return null;
        }
        String password_hash = lista.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i);
        if (argon2.verify(password_hash, usuariocliente.getPassword())){
            return lista.get(0);

        }
        return null;


    }
}
