package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.EmptyInmuebleListException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.InmuebleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;


    public List<Inmueble>findAll(){
        List<Inmueble>result = inmuebleRepository.findAll();

        if(result.isEmpty()){
            throw new EmptyInmuebleListException();
    }
        return result;
    }

}
