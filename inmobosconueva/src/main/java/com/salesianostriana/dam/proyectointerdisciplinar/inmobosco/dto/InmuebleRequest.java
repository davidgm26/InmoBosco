package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Tipo;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.TipoRepository;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InmuebleRequest {

    private double precio;
    private String ubicacion;
    private double metrosCuadrados;
    private String provincia;
    private String descripcion;
    protected String tipoInmueble;
    private int numBanios;
    private int numHab;

}
