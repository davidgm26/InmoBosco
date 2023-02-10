package com.salesianostriana.dam.proyectoinmo.inmobosco.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @Column(name = "fotos")
    @Builder.Default
    private List<String>fotos = new ArrayList<>();
    // private String tipo; esto de momento se queda en cuarentena hasta aclarar completamente todo
    private String ubicacion;
    private double metrosCuadrados;
    private String provincia;
    private String descripcion;


}
