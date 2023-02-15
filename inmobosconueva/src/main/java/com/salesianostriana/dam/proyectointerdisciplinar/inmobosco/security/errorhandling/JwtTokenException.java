package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg) {
        super(msg);
    }


}
