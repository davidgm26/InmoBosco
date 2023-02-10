package com.salesianostriana.dam.proyectoinmo.inmobosco.exception;

import javax.persistence.EntityNotFoundException;

public class EmptyInmuebleListException extends EntityNotFoundException {

    public EmptyInmuebleListException(){
        super("No hay inmuebles disponibles");
    }

}
