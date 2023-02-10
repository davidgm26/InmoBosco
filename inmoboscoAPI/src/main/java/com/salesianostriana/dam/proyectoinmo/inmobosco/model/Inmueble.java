package com.salesianostriana.dam.proyectoinmo.inmobosco.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class Inmueble {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "propietario_id")
    private Usuario propietario;

    private double precio;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> img = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private Tipo tipoInmueble;

    private String ubicacion;

    private double metrosCuadrados;
    private String provincia;
    private String descripcion;

}

