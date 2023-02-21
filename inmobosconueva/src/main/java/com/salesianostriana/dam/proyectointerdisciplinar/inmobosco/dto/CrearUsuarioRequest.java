package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation.PasswordsMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
@PasswordsMatch(passwordField = "password", verifyPasswordField = "passwordRepeat" ,message = "")
public class CrearUsuarioRequest {

    @NotEmpty(message = "{CrearUsuarioRequest.nombre.notempty}")
    private String nombre;

    @NotEmpty(message = "{CrearUsuarioRequest.apellidos.notempty}")
    private String apellidos;

   // @NotEmpty(message = "{CrearUsuarioRequest.password.notempty}")
    private String password;

    //@NotEmpty(message = "{CrearUsuarioRequest.passwordRepeat.notempty}")
    private String passwordRepeat;

    @NotEmpty(message = "{CrearUsuarioRequest.userName.notempty}")
    private String userName;

    private String dni;

    private int edad;

    private String avatar;

    private LocalDate fechaNacimiento;

    private String telefono;

    private String email;
}
