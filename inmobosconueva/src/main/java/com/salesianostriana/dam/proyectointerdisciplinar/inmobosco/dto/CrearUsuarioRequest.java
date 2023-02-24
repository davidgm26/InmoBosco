package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation.PasswordsMatch;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.validation.annotation.UniqueUserName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PasswordsMatch.List({@PasswordsMatch(passwordField = "password", verifyPasswordField = "passwordRepeat", message = "")})
public class CrearUsuarioRequest {

    @NotEmpty(message = "{CrearUsuarioRequest.nombre.notempty}")
    private String nombre;

    @NotEmpty(message = "{CrearUsuarioRequest.apellidos.notempty}")
    private String apellidos;

    @NotEmpty(message = "{CrearUsuarioRequest.password.notempty}")
    private String password;

    @NotEmpty(message = "{CrearUsuarioRequest.passwordRepeat.notempty}")
    private String passwordRepeat;

    @NotEmpty(message = "{CrearUsuarioRequest.userName.notempty}")
    @UniqueUserName(message = "{CrearUsuarioRequest.userName.uniqueUserName}")
    private String userName;

    @NotEmpty(message = "{CrearUsuarioRequest.dni.notempty}")
    private String dni;

    @NotNull(message = "{CrearUsuarioRequest.edad.notempty}")
    private int edad;

    @NotEmpty(message = "{CrearUsuarioRequest.avatar.notempty}")
    private String avatar;

    private LocalDate fechaNacimiento;

    @NotEmpty(message = "{CrearUsuarioRequest.telefono.notempty}")
    private String telefono;

    @NotEmpty(message = "{CrearUsuarioRequest.email.notempty}")
    private String email;
}
