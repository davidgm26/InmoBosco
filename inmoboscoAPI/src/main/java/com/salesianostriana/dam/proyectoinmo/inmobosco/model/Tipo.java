package com.salesianostriana.dam.proyectoinmo.inmobosco.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String tipoInmuble;

    @OneToOne(mappedBy = "tipoInmueble",cascade = CascadeType.ALL,orphanRemoval = true)
    private Inmueble inmueble;




}
