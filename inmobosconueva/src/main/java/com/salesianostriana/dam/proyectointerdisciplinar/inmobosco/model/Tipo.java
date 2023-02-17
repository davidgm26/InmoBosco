package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model;

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

    private String tipoInmueble;

    @OneToOne(mappedBy = "tipoInmueble")
    private Inmueble inmueble;





}
