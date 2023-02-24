package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service;

import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleRequest;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.EmptyInmuebleListException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.exception.InmuebleNotFoundException;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Tipo;
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
import java.util.Optional;


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

    public Page<Inmueble> search(List<SearchCriteria> params, Pageable pageable) {
        GenericSpecificationBuilder<Inmueble> inmuebleGenericSpecificationBuilder =
                new GenericSpecificationBuilder<>(params);

        Specification<Inmueble> spec = inmuebleGenericSpecificationBuilder.build();
        return inmuebleRepository.findAll(spec, pageable);
    }

    public Page<InmuebleResponse> buscarTodosDeUnTipo(String tipo, Pageable pageable) {
        Page<Inmueble> result = inmuebleRepository.todosDeUnTipoInmbueble(tipo, pageable);
        Page<InmuebleResponse> inmuebleResponsePage = new PageImpl<>(result.stream().toList(), pageable, result.getTotalPages()).map(InmuebleResponse::fromInmueble);
        return inmuebleResponsePage;
    }

    public Inmueble crearInmueble(InmuebleRequest inmuebleRequest) {
        Inmueble i = Inmueble.builder()
                .descripcion(inmuebleRequest.getDescripcion())
                .metrosCuadrados(inmuebleRequest.getMetrosCuadrados())
                .ubicacion(inmuebleRequest.getUbicacion())
                .precio(inmuebleRequest.getPrecio())
                .provincia(inmuebleRequest.getTipoInmueble())
                //.tipoInmueble(tipoRepository.findByName(inmuebleRequest.getTipoInmueble()))
                //.tipoInmueble(tipoRepository.findByName(inmuebleRequest.getTipoInmueble()))
                .build();

        return inmuebleRepository.save(i);
    }

    //El tipo se asigna null

    public InmuebleResponse edit(InmuebleRequest inmuebleRequest, Long id) {
        /*
        List<Tipo>morite = tipoRepository.findAll();
        morite.forEach(tipo -> System.out.println(tipo.getTipoInmueble()));
        */
        //String tipo = tipoRepository.findByName(inmuebleRequest.getTipoInmueble()).toString();
         Inmueble i = inmuebleRepository.findById(id).map(inmueble -> {
            inmueble.setPrecio(inmuebleRequest.getPrecio());
            //inmueble.setTipoInmueble(tipoRepository.findFirstByTipoInmuebleContainsIgnoreCase(inmuebleRequest.getTipoInmueble()));
            inmueble.setTipoInmueble(tipoRepository.findByName(inmuebleRequest.getTipoInmueble()));
            //inmueble.setTipoInmueble(tipoRepository.findFirstBytipoInmuebleContainsIgnoreCase(inmuebleRequest.getTipoInmueble()));
            //inmueble.setTipoInmueble(buscarTipo(inmuebleRequest.getTipoInmueble()));
           /* if(inmuebleRequest.getTipoInmueble().equalsIgnoreCase("casa"))
                inmueble.setTipoInmueble(tipoRepository.findById(1L).get());

            */
            inmueble.setProvincia(inmuebleRequest.getProvincia());
            inmueble.setUbicacion(inmuebleRequest.getUbicacion());
            inmueble.setDescripcion(inmuebleRequest.getDescripcion());
            inmueble.setMetrosCuadrados(inmuebleRequest.getMetrosCuadrados());
            return inmuebleRepository.save(inmueble);
        }).orElseThrow(() -> new InmuebleNotFoundException(id));

         return InmuebleResponse.fromInmueble(i);
    }

    public void delete(Long id) throws InmuebleNotFoundException {
        Optional<Inmueble> i = inmuebleRepository.findById(id);
        if (i.isPresent()) {
            inmuebleRepository.deleteById(id);
        } else {
            throw new InmuebleNotFoundException(id);
        }

    }
/*
    public Tipo buscarTipo(String tipo){
        if (tipo.equalsIgnoreCase("Casa")){
            return tipoRepository.findById(1L).get();
        } else if (tipo.equalsIgnoreCase("Piso")){
            return tipoRepository.findById(2L).get();
        }else{
            return tipoRepository.findById(3L).get();


        }

    }
    */


}
