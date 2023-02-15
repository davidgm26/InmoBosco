package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CrearUsuarioRequest {

    private String nombre;

    private String apellidos;

    private String password;

    private String username;

    private String dni;

    private int edad;

    private String avatar;

    private Date fechaNacimiento;

    private String telefono;

    private String email;
}
