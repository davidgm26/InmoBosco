package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleRequest;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.EmptyInmuebleListException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.InmuebleNotFoundException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.InmuebleRepository;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.TipoRepository;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.spec.GenericSpecificationBuilder;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteria;
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
    private final TipoRepository tipoRepository;


    public Page<InmuebleResponse> findAll(List<SearchCriteria> params, Pageable pageable) {
        GenericSpecificationBuilder<Inmueble> inmuebleGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params);


        Specification<Inmueble> spec = inmuebleGenericSpecificationBuilder.build();
        Page<InmuebleResponse> result = inmuebleRepository.findAll(spec, pageable).map(i -> inmuebleRepository.nuevoDto(i.getId()));

        if (result.isEmpty())
            throw new EmptyInmuebleListException();

        return result;
    }

    public InmuebleResponse findById(Long id) {
        return inmuebleRepository.findById(id)
                .map(InmuebleResponse::fromInmueble)
                .orElseThrow(() -> new InmuebleNotFoundException(id));
    }

    public Page<InmuebleResponse> buscarTodosDeUnTipo(String tipo, Pageable pageable) {
        Page<Inmueble> result = inmuebleRepository.todosDeUnTipoInmbueble(tipo, pageable);
        Page<InmuebleResponse> inmuebleResponsePage = new PageImpl<>(result.stream().toList(), pageable, result.getTotalPages()).map(InmuebleResponse::fromInmueble);
        return inmuebleResponsePage;
    }

    public Inmueble save(InmuebleRequest inmbuebleRequest) {
        Inmueble i = Inmueble.builder().build();
        return inmuebleRepository.save(i);
    }

    public Inmueble edit(InmuebleRequest inmuebleRequest, Long id) {
        return inmuebleRepository.findById(id).map(inmueble -> {
                    inmueble.setPrecio(inmuebleRequest.getPrecio());
                    inmueble.setTipoInmueble(tipoRepository.findByName(inmuebleRequest.getTipoInmueble()));
                    inmueble.setProvincia(inmuebleRequest.getProvincia());
                    inmueble.setUbicacion(inmuebleRequest.getUbicacion());
                    inmueble.setDescripcion(inmuebleRequest.getDescripcion());
                    inmueble.setMetrosCuadrados(inmuebleRequest.getMetrosCuadrados());
                    return inmuebleRepository.save(inmueble);
                })
                .orElseThrow(() -> new InmuebleNotFoundException(id));

    }

    public void delete(Long id) {
        if (inmuebleRepository.existsById(id))
            inmuebleRepository.deleteById(id);
    }


}
