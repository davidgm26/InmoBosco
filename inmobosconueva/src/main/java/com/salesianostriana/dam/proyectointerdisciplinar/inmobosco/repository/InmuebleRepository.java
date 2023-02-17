package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble,Long> {

    List<Inmueble> findByPropietario(String propietario);
}
