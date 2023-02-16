package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.InmuebleRepository;
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
