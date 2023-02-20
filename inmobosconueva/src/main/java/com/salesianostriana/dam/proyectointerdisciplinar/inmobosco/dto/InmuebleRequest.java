package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String tipoInmueble;

}
