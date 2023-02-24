package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.controller;


import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleRequest;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.dto.InmuebleResponse;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Inmueble;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.model.Usuario;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteriaExtractor;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.service.InmuebleService;
import com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.search.util.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmueble")
public class InmuebleController {


    private final InmuebleService inmuebleService;


    @Operation(summary = "Obtiene todos los inmuebles paginados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la lista de viviendas paginadas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InmuebleResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "content": [
                                                     {
                                                         "tipo": "Casa",
                                                         "descripcion": "Apartamento reformado con vistas al río. Cocina americana con electrodomÃ©sticos. Suelos de tarima. Totalmente amueblado. Zona muy tranquila.",
                                                         "precio": 125000.0,
                                                         "ubicacion": "Triana",
                                                         "metrosCuadrados": 80.5,
                                                         "numBanios": 2,
                                                         "numHab": 1
                                                     },
                                                     {
                                                         "tipo": "Piso",
                                                         "descripcion": "Piso muy luminoso, 3 habitaciones y 2 baÃ±os. Cocina amueblada. Suelos de mÃ¡rmol. Garaje y trastero incluidos. Zona residencial tranquila.",
                                                         "precio": 240000.0,
                                                         "ubicacion": "Los Remedios",
                                                         "metrosCuadrados": 120.0,
                                                         "numBanios": 3,
                                                         "numHab": 2
                                                     },
                                                     {
                                                         "tipo": "Adosado",
                                                         "descripcion": "Ã�tico de lujo con terraza y vistas a la ciudad. 4 habitaciones y 3 baÃ±os. Cocina completamente equipada. Suelos de madera. Aire acondicionado centralizado. Zona cÃ©ntrica y bien comunicada.",
                                                         "precio": 340000.0,
                                                         "ubicacion": "El Porvenir",
                                                         "metrosCuadrados": 180.0,
                                                         "numBanios": 4,
                                                         "numHab": 3
                                                     },
                                                     {
                                                         "tipo": "Casa",
                                                         "descripcion": "Acogedor apartamento en zona cÃ©ntrica. Totalmente reformado y amueblado. 1 habitaciÃ³n y 1 baÃ±o. Suelos de tarima. Aire acondicionado y calefacciÃ³n.",
                                                         "precio": 180000.0,
                                                         "ubicacion": "San Bernardo",
                                                         "metrosCuadrados": 90.0,
                                                         "numBanios": 1,
                                                         "numHab": 1
                                                     },
                                                     {
                                                         "tipo": "Piso",
                                                         "descripcion": "Piso amplio y luminoso con balcÃ³n. 3 habitaciones y 2 baÃ±os. Cocina amueblada. Suelos de terrazo. Aire acondicionado centralizado. Zona comercial y bien comunicada.",
                                                         "precio": 295000.0,
                                                         "ubicacion": "NerviÃ³n",
                                                         "metrosCuadrados": 140.0,
                                                         "numBanios": 3,
                                                         "numHab": 2
                                                     }
                                                 ],
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "Full authentication is required to access this resource",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT",
                    content = @Content),
    })
    @GetMapping("/")
    public Page<InmuebleResponse> listarTodosLosInmuebles(@RequestParam(value = "search", defaultValue = "") String search,
                                                          @PageableDefault(size = 5, page = 0) Pageable pageable) {

        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(search);
        return inmuebleService.findAll(params, pageable);

    }

    @Operation(summary = "Obtiene un inmueble por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha obtenido correctamente la vivienda por su identificador",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InmuebleResponse.class),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                 "tipo": "Piso",
                                                 "descripcion": "Piso amplio y luminoso con balcón. 3 habitaciones y 2 baños. Cocina amueblada. Suelos de terrazo. Aire acondicionado centralizado. Zona comercial y bien comunicada.",
                                                 "precio": 295000.0,
                                                 "ubicacion": "NerviÃ³n",
                                                 "metrosCuadrados": 140.0,
                                                 "numBanios": 2,
                                                 "numHab": 3
                                             }
                                            """
                            )})}),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el inmueble con el identificador proporcionado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public InmuebleResponse listarUnInmueble(@PathVariable Long id) {
        return inmuebleService.findById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Page<InmuebleResponse> listarTodosDeUnTipo(@PathVariable String tipo, Pageable pageable) {
        return inmuebleService.buscarTodosDeUnTipo(tipo, pageable);

    }

    @PostMapping("/")
    public Inmueble crearInmueble(@RequestBody InmuebleRequest inmuebleRequest, @RequestPart("files") MultipartFile file) {
        return inmuebleService.crearInmueble(inmuebleRequest,file);
    }

    @PutMapping("/{id}")
    public InmuebleResponse editarUnInmueble(@PathVariable Long id, @RequestBody InmuebleRequest inmuebleRequest) {
        return inmuebleService.edit(inmuebleRequest, id);
    }


    @Operation(summary = "Elimina un inmueble por su identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Se ha eliminado correctamente el inmueble seleccionado",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "No tienes aurorización para realizar esta petición",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Se ha expirado el token JWT",
                    content = @Content),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado el inmueble",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        inmuebleService.delete(id);
        return ResponseEntity.noContent().build();
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
