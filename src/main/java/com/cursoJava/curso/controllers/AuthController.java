package com.cursoJava.curso.controllers;

import com.cursoJava.curso.dao.UsuarioDao;
import com.cursoJava.curso.models.Usuario;
//import com.cursoJava.curso.dao.UsuarioDao;

import com.cursoJava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)//metodo para acceder aun usuario
    public String login(@RequestBody Usuario usuario){

        Usuario logUser=usuarioDao.obtenerUsuarioPorCredenciales(usuario);

        if(logUser!=null){

            String tokenJWT =  jwtUtil.create(String.valueOf(logUser.getId()),logUser.getEmail());

            return tokenJWT;
        }
        return "FAIL";
    }

}
