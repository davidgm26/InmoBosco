package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@Builder
public class Inmueble {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id",foreignKey = @ForeignKey(name = "FK_INMUEBLE_USUARIO"))
    @CreatedBy
    private Usuario propietario;


    private double precio;

    // libreria Vlad mihalcea hypersistence-utils
    //@ElementCollection(targetClass=String.class)
    private String img;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    private Tipo tipoInmueble;

    @NotEmpty
    private String ubicacion;

    private double metrosCuadrados;

    private String provincia;

    private String descripcion;

    @ManyToMany(mappedBy = "inmueblesFav")
    private List<Usuario>usuariosFav;

    private int numHab;

    private int numBanios;



}

