package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUserResponse extends CrearUsuarioResponse{

    private String token;

    public JwtUserResponse(CrearUsuarioResponse crearUsuarioResponse) {
        id = crearUsuarioResponse.getId();
        nombre = crearUsuarioResponse.getNombre();
        apellido = crearUsuarioResponse.getApellido();
        avatar = crearUsuarioResponse.getAvatar();
        fechaCreacion = crearUsuarioResponse.getFechaCreacion();
    }

    public static JwtUserResponse of (Usuario user, String token) {
        JwtUserResponse result = new JwtUserResponse(CrearUsuarioResponse.fromUsuario(user));
        result.setToken(token);
        return result;

    }


    }
