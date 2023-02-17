package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble,Long>, JpaSpecificationExecutor<Inmueble> {

    List<Inmueble> findByPropietario(String propietario);
}
