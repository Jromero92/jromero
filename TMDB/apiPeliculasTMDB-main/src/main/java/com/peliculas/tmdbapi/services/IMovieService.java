package com.peliculas.tmdbapi.services;

import com.peliculas.tmdbapi.model.movies.Movie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define operaciones para interactuar con películas en un servicio.
 * Proporciona métodos para obtener peliculas de la api, guardar películas consultadas en la base de datos y
 * recuperar películas previamente consultadas.
 *
 * @see Movie
 */

@Service
public interface IMovieService {
    /**
     * Obtiene un listado de películas de la api externa realizdo una busqueda por un título ingresado por el usuario.
     * Una vez que tiene el listado, guarda las películas en la base de datos agregando a los datos traídos la
     * fecha de consulta y la palabra clave utilizada en la búsqueda
     *
     * @param title Título de la película que se desea obtener.
     * @return La película correspondiente al título especificado, o {@code null} si no se encuentra.
     */
    public List<Movie> getMovie(String title) throws IOException, InterruptedException;

    /**
     * Permite guardar una película en la base de datos.
     *
     * @param movie La película que se va a guardar.
     */
    public void saveMovie(Movie movie, String searchKeyword);

    /**
     * Obtiene la lista de películas guardadas en la base de datos.
     *
     * @return Lista de películas almacenadas en la base de datos.
     */
    public List<Movie> getMoviesSaved(LocalDate consultationDate);


    public List<Movie> getUpcomingMovies() throws IOException, InterruptedException;

}
