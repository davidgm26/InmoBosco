package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation.PasswordsMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatch(passwordField = "password", verifyPasswordField = "passwordRepeat" ,message = "")
public class CrearUsuarioRequest {

    private String nombre;

    private String apellidos;

    private String password;

    private String passwordRepeat;

    private String userName;

    private String dni;

    private int edad;

    private String avatar;

    private LocalDate fechaNacimiento;

    private String telefono;

    private String email;
}
