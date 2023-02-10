package com.salesianostriana.dam.proyectoinmo.inmobosco.repository;

import com.salesianostriana.dam.proyectoinmo.inmobosco.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble,Long> {

}
