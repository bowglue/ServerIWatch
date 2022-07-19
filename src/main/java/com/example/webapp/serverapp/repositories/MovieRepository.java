package com.example.webapp.serverapp.repositories;

import com.example.webapp.serverapp.models.Movie;
import com.example.webapp.serverapp.responses.ImageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    interface MovieInterface{
        Long getMovie_id();
        String getMovie_name();
        byte[] getMovie_image();
        byte[] getMovie_title();
        byte[] getMovie_focus();
    }
    @Query(value = "SELECT movie_id as movie_id, movie_image as movie_image, movie_name as movie_name, movie_title as movie_title from movie")
    List<MovieInterface> findAllMovieImage(Pageable pageable);
    @Query(value = "SELECT movie_id as movie_id, movie_focus as movie_focus from movie where movie_id = ?1")
    MovieInterface findFocusImage(Long id);

    @Query(value = "SELECT movie_id as movie_id, movie_focus as movie_focus, movie_title as movie_title from movie")
    List<MovieInterface> findHeaderImage(Pageable pageable);

}
