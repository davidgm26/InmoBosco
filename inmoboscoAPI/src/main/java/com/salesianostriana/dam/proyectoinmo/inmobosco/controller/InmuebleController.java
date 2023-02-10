package com.salesianostriana.dam.proyectoinmo.inmobosco.controller;

import com.salesianostriana.dam.proyectoinmo.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectoinmo.inmobosco.service.InmuebleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class InmuebleController {

    private InmuebleService inmuebleService;

    @GetMapping("/")
    public List<Inmueble> getAll(){
        return inmuebleService.findAll();
    }


}
