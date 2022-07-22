package com.example.webapp.serverapp.responses;

import com.example.webapp.serverapp.models.Movie;
import com.example.webapp.serverapp.repositories.MovieInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class FocusResponse {
    public MovieInterface movieFocus;
    public List<MovieInterface> movieSuggestion;

    public MovieInterface getMovieFocus() {
        return movieFocus;
    }

    public void setMovieFocus(MovieInterface movieFocus) {
        this.movieFocus = movieFocus;
    }

    public List<MovieInterface> getMovieSuggestion() {
        return movieSuggestion;
    }

    public void setMovieSuggestion(List<MovieInterface> movieSuggestion) {
        this.movieSuggestion = movieSuggestion;
    }
}
