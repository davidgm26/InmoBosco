package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.EmptyInmuebleListException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.InmuebleRepository;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;


    public Page<InmuebleResponse>findAll(List<SearchCriteria>params, Pageable pageable) {
        GenericSpecificationBuilder<Inmueble> inmuebleGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params);


        Specification<Inmueble> spec = inmuebleGenericSpecificationBuilder.build();
        return inmuebleRepository.findAll(spec, pageable).map(i -> inmuebleRepository.nuevoDto(i.getId()));
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



    public Page<InmuebleResponse>buscarTodosDeUnTipo(String tipo,Pageable pageable){
        Page<Inmueble> result = inmuebleRepository.todosDeUnTipoInmbueble(tipo,pageable);
        Page<InmuebleResponse> inmuebleResponsePage= new PageImpl<>(result.stream().toList(),pageable,result.getTotalPages()).map(InmuebleResponse::fromInmueble);
        return inmuebleResponsePage;
    }


}
