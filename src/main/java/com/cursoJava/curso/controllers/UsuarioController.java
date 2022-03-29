package com.cursoJava.curso.controllers;


import com.cursoJava.curso.dao.UsuarioDao;
import com.cursoJava.curso.models.Usuario;
import com.cursoJava.curso.utils.JWTUtil;
import de.mkammerer.argon2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController  {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuario/{id}", method = RequestMethod.GET)//metodo para acceder aun usuario
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario=new Usuario();
        usuario.setId(id);
        usuario.setNombre("daniel");
        usuario.setApellido("rodriguez");
        usuario.setEmail("daniel@gmail.com");
        usuario.setTelefono("3203521720");
        return usuario;

    }


    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)//metodo para acceder aun usuario
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){

        if (!validarToken(token)) {
            return null;
        }

       return usuarioDao.getUsuarios();

    }

    private boolean validarToken(String token){
        String idUsuario=jwtUtil.getKey(token);

        return  idUsuario != null;

    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)//metodo para acceder aun usuario
    public void RegistrarUsuario(@RequestBody Usuario usuario){

        Argon2 argon2 =Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash=argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registrar(usuario);

    }

    @RequestMapping(value = "usuario1")
    public Usuario editar(){

        Usuario usuario=new Usuario();
        usuario.setNombre("daniel");
        usuario.setApellido("rodriguez");
        usuario.setEmail("daniel@gmail.com");
        usuario.setTelefono("3203521720");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)//para borrar un usuario
    public void eliminar(@RequestHeader(value = "Authorization") String token,
                         @PathVariable Long id){
        if (!validarToken(token)) {
            return ;
        }

        usuarioDao.eliminar(id);
    }
}
