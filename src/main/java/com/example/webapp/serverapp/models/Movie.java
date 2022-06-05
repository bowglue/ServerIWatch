package com.example.webapp.serverapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;

@Entity(name = "movie")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movie_id;
    private String movie_name;
    @Lob
    private String movie_image;

    /*private String movie_title;
    private String movie_focus;*/

    public Long getMovie_id() {
       return movie_id;
    }
    public void setMovie_id(Long movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }
    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_image() {
        return movie_image;
    }
    public void setMovie_image(String movie_image) {
        this.movie_image = movie_image;
    }

    /*public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_focus() {
        return movie_focus;
    }

    public void setMovie_focus(String movie_focus) {
        this.movie_focus = movie_focus;
    }*/

}
