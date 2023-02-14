package com.salesianostriana.dam.proyectoinmo.inmobosco.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    private UUID id;

    private String nombre;

    private String apellidos;

    private String password;

    private String rol;

    private String username;

    private String dni;

    private int edad;

    private String avatar;

    private Date fechaNacimiento;
    private String telefono;

    private String email;

    @OneToMany(mappedBy = "propietario")
    private List<Inmueble> propiedades = new ArrayList<>();

    @ElementCollection(targetClass=String.class)
    private List<Inmueble> inmueblesFav = new ArrayList<>();

}
