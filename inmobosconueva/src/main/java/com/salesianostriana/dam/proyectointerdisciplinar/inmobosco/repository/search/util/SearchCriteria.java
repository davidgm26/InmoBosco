package com.salesianostriana.dam.proyectointerdisciplinar.inmobosco.repository.search.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private String value;
    private String operator;

}
