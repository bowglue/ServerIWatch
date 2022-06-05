package com.example.webapp.serverapp.responses;

import lombok.Data;

@Data
public class TrailerResponse {
    public TrailerResponse(String movie_name, String movie_trailer){
        this.movie_name = movie_name;
        this.movie_trailer = movie_trailer;
    }
    private String movie_name;
    private String movie_trailer;
}

