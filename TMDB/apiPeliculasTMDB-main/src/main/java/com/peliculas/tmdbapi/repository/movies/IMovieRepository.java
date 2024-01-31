package com.peliculas.tmdbapi.repository.movies;

import com.peliculas.tmdbapi.entities.movies.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findByTitle(String movieTitle);
    List<MovieEntity> findByConsultationDate(LocalDate consultationDate);
}


