package com.salesianostriana.dam.proyectoinmo.inmobosco.security.errorHandling;

public class JwtTokenException extends RuntimeException {

    public JwtTokenException(String msg){
        super(msg);
    }

}
