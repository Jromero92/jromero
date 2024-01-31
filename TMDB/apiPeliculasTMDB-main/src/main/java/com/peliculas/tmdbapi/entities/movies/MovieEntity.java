package com.peliculas.tmdbapi.entities.movies;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * Representa una entidad de película en el sistema.
 * Contiene información detallada sobre una película específica: título, título origina, fecha de lanzamiento,
 * descripción, url del poster, puntaje promedio otorgado por los usuarios de tmdb y cantidad de votos recibidos por
 * la película.
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "movie")
public class MovieEntity {
    /**
     * Clave primaria de la tabla
     */
    @Id
    @OrderBy("id ASC")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Titulo de la película
     */
    @Column (name="title", length=150)
    private String title;

    /**
     * Titulo original
     */
    @Column (name="original_title", length=150)
    private String original_title;

    /**
     * Ruta de la imagen del poster
     */
    @Column(name="poster_path", nullable = true, length=250 )
    private String poster_path;// (foto)

    /**
     * Descripción general de la trama.
     * Definido como Large Object, con length de 550 caracteres
     */

    @Column (name="overview", length=550)
    @Lob
    private String overview;

    /**
     * Fecha de lanzamiento de la película.
     */
    @Column (name="release_date")
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate release_date;

    /**
     * Promedio de calificaciones de los usuarios de tmdb.
     */
    @Column (name="vote_average")
    private Double vote_average;

    /**
     * Cantidad de calificaciones recibidas por la película
     */
    @Column (name="vote_count")
    private Long vote_count;

    /**
     * Fecha en la que se realizó la consulta.
     */
    @Column (name="consultationDate")
    //@Temporal(TemporalType.DATE)
    private LocalDate consultationDate;

    /**
     * Palabra clave utilizada para la búsqueda
     */
    @Column (name="search_keyword")
    private String search_keyword;
}
