package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Tipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface InmuebleRepository extends JpaRepository<Inmueble, Long>, JpaSpecificationExecutor<Inmueble> {

    @Query("""
                select new com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse(
                    t.tipoInmueble,i.descripcion,i.precio,i.ubicacion,i.metrosCuadrados)
                from Inmueble i LEFT JOIN i.tipoInmueble t
                where i.id = :id
                """
    )
    InmuebleResponse nuevoDto(Long id);

    @Query("""
            select i 
            from Inmueble i JOIN Tipo t ON (i.tipoInmueble = t.id)
            where t.tipoInmueble =:tipo
            """)
    Page<Inmueble> todosDeUnTipoInmbueble(@Param("tipo") String tipo, Pageable pageable);
}
