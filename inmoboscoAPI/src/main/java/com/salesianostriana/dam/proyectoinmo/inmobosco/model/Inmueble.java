package com.salesianostriana.dam.proyectoinmo.inmobosco.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Inmueble {

    @Id
    @GeneratedValue
    private Long id;

    private double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id")
    private Usuario propietario;

    private List<String>fotos;
    // private String tipo; esto de momento se queda en cuarentena hasta aclarar completamente todo
    private String ubicacion;
    private double metrosCuadrados;
    private String provincia;
    private String descripcion;


}
