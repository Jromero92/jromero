package com.peliculas.tmdbapi.controller;

import com.peliculas.tmdbapi.model.movies.Movie;
import com.peliculas.tmdbapi.services.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Controlador REST que maneja las operaciones relacionadas con películas.
 * Proporciona endpoints para obtener información de películas de la api externa, guardar películas y
 * obtener la lista de películas guardadas en la base de datos.
 *
 * @see Movie
 * @see IMovieService
 */

@RestController
@RequestMapping("/movies")
public class MovieController {


        //@Qualifier("movieService")
        private final IMovieService movieService;

        /**
         * Constructor que inyecta la dependencia del servicio de películas.
         *
         * @param movieService Servicio de películas que se utilizará en el controlador.
         */
        @Autowired
        public MovieController(IMovieService movieService) {
            this.movieService = movieService;
        }


        /**
         * Obtiene información de una película buscándola por su título.
         *
         * @param title Título de la película cuya información se desea obtener.
         * @return ResponseEntity con la película correspondiente al título especificado
         *         o ResponseEntity con estado 404 si la película no se encuentra.
         */
        @GetMapping("/search/{title}")
        public ResponseEntity<List<Movie>> getMovie(@PathVariable String title) throws IOException, InterruptedException {
            // Lógica para obtener información de una película por título
            return ResponseEntity.ok(movieService.getMovie(title));
        }


        /**
         * Guarda una película.
         *
         * @param movie Película que se va a guardar en la base de datos.
         * @return ResponseEntity con estado 201 (CREATED) si la película se guarda con éxito.
         */
        @PostMapping
        public ResponseEntity<Void> saveMovie(@RequestBody Movie movie) {
            // Permitirá guardar una película en la bbdd
            //falta definir logica
            //movieService.saveMovie(movie);
            //return ResponseEntity.status(HttpStatus.CREATED).build();
            return null;
        }


        /**
         * Obtiene la lista de películas previamente consultadas y que fueron guardadas en la base de datos,
         * buscándolas por la fecha en la que se realizó la consulta
         *
         * @param consultationDate fecha en la que se realizo la consulta a la api externa
         * @return ResponseEntity con la lista de películas guardadas o ResponseEntity con
         *         estado 404 si no hay películas guardadas.
         */
        @GetMapping("/saved/{consultationDate}")
        public ResponseEntity<List<Movie>> getSavedMovies(@PathVariable String consultationDate) {
                //convierte la fecha pasada como String en LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate consultDate = LocalDate.parse(consultationDate, formatter);

            return ResponseEntity.ok(movieService.getMoviesSaved(consultDate));
        }

        @GetMapping("/upcoming")
        public ResponseEntity<List<Movie>> getUpcomingMovies() throws IOException, InterruptedException {
                return ResponseEntity.ok(movieService.getUpcomingMovies());
        }


}
