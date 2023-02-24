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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoInmueble;

    @OneToOne(mappedBy = "tipoInmueble",fetch = FetchType.LAZY)
    private Inmueble inmueble;


}
