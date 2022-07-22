package com.example.webapp.serverapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "genre")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genre_id;

    private String movie_genre;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "genre_mapping",
            joinColumns = @JoinColumn(name = "genre_mapping_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_mapping_id")
    )
    private List<Movie> movie;

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }
}
