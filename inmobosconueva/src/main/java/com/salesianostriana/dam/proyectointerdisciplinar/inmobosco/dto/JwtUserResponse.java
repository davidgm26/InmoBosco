package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
        id
    }
}
