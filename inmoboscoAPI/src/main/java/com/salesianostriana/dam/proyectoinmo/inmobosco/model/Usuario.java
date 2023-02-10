package com.salesianostriana.dam.proyectoinmo.inmobosco.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {

    //En el DTO de respuesta tienes que devolver el rol

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    private String nombre;

    private String apellidos;

    private String password;

    private String rol;

    private String username;

    private String dni;

    private int edad;
    // private Date fechaNacimiento; lo mantenemos en cuarentena hasta solucionar un par de dudas
    private String telefono;

    private String email;

    @OneToMany(mappedBy = "propietario")
    @Builder.Default
    private List<Inmueble> propiedades = new ArrayList<>();
/*
    @Builder.Default
    private List<Inmueble> inmueblesFav = new ArrayList<>();
*/
}
