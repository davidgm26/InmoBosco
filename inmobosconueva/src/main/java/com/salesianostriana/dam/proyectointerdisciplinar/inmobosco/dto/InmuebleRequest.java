package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.TipoRepository;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InmuebleRequest {

    @NotNull
    private double precio;
    @NotEmpty
    private String ubicacion;
    @NotNull
    private double metrosCuadrados;

    private String provincia;

    private String descripcion;

    private String tipoInmueble;

}
