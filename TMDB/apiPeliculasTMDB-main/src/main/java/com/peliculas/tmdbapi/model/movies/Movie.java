package com.peliculas.tmdbapi.model.movies;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
/**
 * Representa una película obtenida de The Movie Database (TMDb).
 *
 * Esta clase encapsula la información detallada de una película, incluyendo su título,
 * título original, ruta del póster, descripción, presupuesto, fecha de lanzamiento, ingresos, etc.
 *
 * @see <a href="https://www.themoviedb.org/documentation/api">The Movie Database API</a>
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    private Long id;
    private String title;
    private String original_title;
    private String poster_path;// (foto)
    private String overview;
    private Date release_date;
    private Double vote_average;
    private Long vote_count;



}
