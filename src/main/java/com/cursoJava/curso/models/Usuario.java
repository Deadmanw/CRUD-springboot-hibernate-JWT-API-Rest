package com.cursoJava.curso.models;
//contiene todas las variables del usuario

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
@ToString
@EqualsAndHashCode
public class Usuario {

    @Getter@Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Getter@Setter
    @Column(name = "nombre")
    private String nombre;

    @Getter@Setter
    @Column(name = "apellido")
    private String apellido;

    @Getter@Setter
    @Column(name = "email")
    private String email;

    @Getter@Setter
    @Column(name = "telefono")
    private String telefono;

    @Getter@Setter
    @Column(name = "password")
    private String password;


}
