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

    private byte[] movie_image;

    private byte[] movie_title;

    private byte[] movie_focus;

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

    public byte[] getMovie_image() {
        return movie_image;
    }
    public void setMovie_image(byte[] movie_image) {
        this.movie_image = movie_image;
    }

    public byte[] getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(byte[] movie_title) {
        this.movie_title = movie_title;
    }

    public byte[] getMovie_focus() {
        return movie_focus;
    }

    public void setMovie_focus(byte[] movie_focus) {
        this.movie_focus = movie_focus;
    }

}
