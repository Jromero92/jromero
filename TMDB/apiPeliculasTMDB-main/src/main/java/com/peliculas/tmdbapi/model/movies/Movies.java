package com.peliculas.tmdbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Movies es una clase generada para recibir la información de la api externa
 * Contiene el número de la página devuelta por la api externa, la lista de películas incluída en esa página,
 * la cantidad total de páginas encontradas y la cantidad total de películas que se encontraron para la búsqueda
 * realizada.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {
    private Integer page;
    private List<Movie> results;
    private Integer total_pages;
    private Integer total_results;
}
