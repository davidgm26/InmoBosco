package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InmuebleResponse {

    protected String tipo;
    protected String descripcion;
    protected double precio;
    protected String ubicacion;
    protected double metrosCuadrados;
    protected int numBanios;
    protected int numHab;


    public static InmuebleResponse fromInmueble(Inmueble inmueble){
        return InmuebleResponse.builder()
                .descripcion(inmueble.getDescripcion())
                .tipo(inmueble.getTipoInmueble().getTipoInmueble())
                .ubicacion(inmueble.getUbicacion())
                .precio(inmueble.getPrecio())
                .metrosCuadrados(inmueble.getMetrosCuadrados())
                .numHab(inmueble.getNumHab())
                .numBanios(inmueble.getNumBanios())
                .build();
    }
}
