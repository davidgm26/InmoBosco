package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.InmuebleService;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class InmuebleController {

    private final InmuebleService inmuebleService;

    @GetMapping("/")
    public List<InmuebleResponse> listarTodosLosInmuebles(@RequestParam(value = "search", required = false)String search) {

        return inmuebleService.findAll();
    }


    @GetMapping("/{id}")
    public InmuebleResponse listarUnInmueble(@PathVariable Long id){
        return inmuebleService.findById(id);
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
