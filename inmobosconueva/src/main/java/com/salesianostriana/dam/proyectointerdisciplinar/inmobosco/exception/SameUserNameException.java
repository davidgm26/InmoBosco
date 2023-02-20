package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception;

public class SameUserNameException extends RuntimeException {

    public SameUserNameException(){
        super("Ya hay un usuario registrado con ese nombre de usario");
    }

}
