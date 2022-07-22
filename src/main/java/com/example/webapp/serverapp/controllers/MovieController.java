package com.example.webapp.serverapp.controllers;

import com.example.webapp.serverapp.models.Movie;
import com.example.webapp.serverapp.repositories.MovieInterface;
import com.example.webapp.serverapp.repositories.MovieRepository;
import com.example.webapp.serverapp.responses.FocusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/test")
    @Transactional
    public List<Movie> movie(){
        return movieRepository.findAll();
    }

    @GetMapping("/image")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    @Transactional
    public List<MovieInterface> listImage(@RequestParam("page") int page, @RequestParam("numberMovie") int numberMovie) {
        Pageable number = PageRequest.of(page, numberMovie, Sort.by("movie_id"));
        return movieRepository.findAllMovieImage(number);
    }

    @GetMapping("/filter")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    public List<MovieInterface> filterGenre(@RequestParam("genre") String[] genre) {
        return movieRepository.filterByGenre(genre);
    }
    @GetMapping("/more/{id}")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    public FocusResponse recommendation(@PathVariable Long id) {
        Pageable number = PageRequest.of(0, 3);

        MovieInterface movieFocus = movieRepository.findFocusImage(id);
        String[] genre =  movieRepository.FindGenre(id);
        List<MovieInterface>  movieRecommendation =  movieRepository.RecommendByGenre(number, genre, id);

        FocusResponse focusResponse = new FocusResponse();
        focusResponse.setMovieFocus(movieFocus);
        focusResponse.setMovieSuggestion(movieRecommendation);

        return focusResponse;
    }

    @GetMapping("/focus/{id}")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    @Transactional
    public FocusResponse focusImageAndRecommendation(@PathVariable Long id) {
        Pageable number = PageRequest.of(0, 3);

        MovieInterface movieFocus = movieRepository.findFocusImage(id);
        String[] genre =  movieRepository.FindGenre(id);
        List<MovieInterface>  movieRecommendation =  movieRepository.RecommendByGenre(number, genre, id);

        FocusResponse focusResponse = new FocusResponse();
        focusResponse.setMovieFocus(movieFocus);
        focusResponse.setMovieSuggestion(movieRecommendation);

        return focusResponse;
    }

    @GetMapping("/header")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    @Transactional
    public List<MovieInterface> headerImage(@RequestParam("page") int page, @RequestParam("numberMovie") int numberMovie) {
        Pageable number = PageRequest.of(page, numberMovie, Sort.by("movie_id"));
        return movieRepository.findHeaderImage(number);
    }


    @PostMapping("/upload")
    @CrossOrigin(origins = "https://frostwatch.herokuapp.com")
    public Movie uploadImage(@RequestBody final Movie movie) {
        return movieRepository.saveAndFlush(movie);
    }

}
