package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Tipo;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble,Long>, JpaSpecificationExecutor<Inmueble> {

@Query("""
        select new com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse(
        t.tipoinmueble,i.descripcion,i.metrosCuadrados,i.precio,i.ubicacion)
        from Inmueble i LEFT JOIN p.tipo t
        where i.tipo ILIKE :tipo
        """)
List<InmuebleResponse> todosDeUnTipo(@Param("tipo")String tipo);
}
