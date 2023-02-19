package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.InmuebleService;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class InmuebleController {

    private final InmuebleService inmuebleService;

    @GetMapping("/")
    public Page<InmuebleResponse> listarTodosLosInmuebles(@RequestParam(value = "search",defaultValue = "") String search,
                                                          @PageableDefault(size = 20, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);

        return inmuebleService.findAll(params,pageable);
    }


    @GetMapping("/{id}")
    public InmuebleResponse listarUnInmueble(@PathVariable Long id) {
        return inmuebleService.findById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Page<InmuebleResponse> listarTodosDeUnTipo(@PathVariable String tipo, Pageable pageable) {
        return inmuebleService.buscarTodosDeUnTipo(tipo, pageable);

    }






/*
    @PreAuthorize("@inmuebleService.findById(#id).orElse(new net.openwebinars.springboot.restjwt.note.model.Note()).author == authentication.principal.getId().toString()")
    @PutMapping("/{id}")
    public ResponseEntity<Note> edit(@PathVariable Long id, @RequestBody Note edited) {


        return ResponseEntity.of(
                repository.findById(id)
                        .map(note -> {
                            note.setTitle(edited.getTitle());
                            note.setContent(edited.getContent());
                            //note.setAuthor(edited.getAuthor());
                            note.setImportant(edited.isImportant());
                            return repository.save(note);
                        }));



    }

 */
}
