package com.cursoJava.curso.dao;

import com.cursoJava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
    EntityManager entityManager;//manejador de las entidades


    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query="FROM Usuario";//consulta a la base de datos
        return entityManager.createQuery(query).getResultList();//retornamos el resultado de la consulta
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario =entityManager.find(Usuario.class,id);//encontramos el usuario por medio de la id que recibimos
        entityManager.remove(usuario);//eliminamos el usuario
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);//agregamos el nuevo usuario que estamos recibiendo
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String query="FROM Usuario WHERE email = :email ";//consulta a la base de datos
        List<Usuario> lista = entityManager.createQuery(query)
                .setParameter("email",usuario.getEmail())
                .getResultList();//retornamos el resultado de la consulta

        if (lista.isEmpty()) {
            return null ;
        }

        String passwordHashed =lista.get(0).getPassword();

        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if (argon2.verify(passwordHashed, usuario.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
}
