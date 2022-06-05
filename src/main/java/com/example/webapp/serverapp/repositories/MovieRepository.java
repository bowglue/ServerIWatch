package com.example.webapp.serverapp.repositories;

import com.example.webapp.serverapp.models.Movie;
import com.example.webapp.serverapp.responses.ImageResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    interface MovieInterface{
        Long getMovie_id();
        String getMovie_name();
        String getMovie_image();
    }
    @Query(value = "SELECT movie_id as movie_id,movie_image as movie_image from movie")
    List<MovieInterface> findAllMovieImage();

   /*@Query(value = "SELECT movie_name as movie_name, movie_trailer as movie_trailer from movie where movie_id = ?1")
    MovieInterface findMovieTrailerById(Long id);*/
}
