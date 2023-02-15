package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.InmuebleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class InmuebleController {

    private InmuebleService inmuebleService;

    @GetMapping("/")
    public List<Inmueble> getAll() {
        return inmuebleService.findAll();
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
