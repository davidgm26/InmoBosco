package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.EmptyInmuebleListException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.InmuebleRepository;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;


    public List<InmuebleResponse>findAll(){
        List<Inmueble>result = inmuebleRepository.findAll();


        if(result.isEmpty()){
            throw new EmptyInmuebleListException();
    }
        return result.stream()
                .map(InmuebleResponse::fromInmueble)
                .toList();
    }

    public InmuebleResponse findById(Long id){
        return inmuebleRepository.findById(id)
                .map(InmuebleResponse::fromInmueble)
                .orElseThrow(EmptyInmuebleListException::new);
    }

    public Page<Inmueble> search(List<SearchCriteria>params, Pageable pageable){
        GenericSpecificationBuilder<Inmueble> inmuebleGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params);

        Specification<Inmueble> spec = inmuebleGenericSpecificationBuilder.build();
        return inmuebleRepository.findAll(spec,pageable);
    }


}
