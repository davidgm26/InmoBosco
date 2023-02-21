package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public class CrearUsuarioResponse {

    protected String id,userName,avatar,nombre,apellido,role;

    protected LocalDate fechaCreacion;


    public static CrearUsuarioResponse fromUsuario(Usuario user){
        return CrearUsuarioResponse.builder()
                .nombre(user.getNombre())
                .apellido(user.getApellidos())
                .id(user.getId().toString())
                .avatar(user.getAvatar())
                .fechaCreacion(user.getFechaCreacion())
                .userName(user.getUsername())
                .build();
    }
}
