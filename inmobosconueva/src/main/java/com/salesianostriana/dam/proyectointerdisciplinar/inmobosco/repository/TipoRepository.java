package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoRepository extends JpaRepository<Tipo,Long> {


    Tipo findFirstByTipoInmuebleContains(String tipo);
/*
    @Query("""
            SELECT t
            from Tipo t
            where t.tipoInmueble =:tipo
            """)
     Tipo findByName(String tipo);
*/

}

